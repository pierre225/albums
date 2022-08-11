package com.pierre.data

import com.pierre.data.remote.RemoteDataSource
import com.pierre.data.repository.SongsRepositoryImpl
import com.pierre.data.repository.model.DataSong
import com.pierre.data.room.datasource.RoomDataSource
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class SongsRepositoryImplTest {

    @RelaxedMockK
    private lateinit var roomDataSource: RoomDataSource

    @RelaxedMockK
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: SongsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        repository = SongsRepositoryImpl(
            roomDataSource = roomDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `preload song checks if the songs already exist in the DB`() = runTest {
        repository.preloadSongs()
        coVerify { roomDataSource.getSongsCount() }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `preload song calls remote data source if there are no songs in room and will store the result it in room`() = runTest {
        val remoteSongs = mockk<List<DataSong.RemoteSong>>(relaxed = true)

        coEvery { roomDataSource.getSongsCount() }.returns(0)
        coEvery { remoteDataSource.remoteSongs() }.returns(remoteSongs)

        repository.preloadSongs()

        coVerifyOrder {
            roomDataSource.getSongsCount()
            remoteDataSource.remoteSongs()
            roomDataSource.insertSongs(remoteSongs)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `preload song will not call remote data source if room contains data`() = runTest {
        coEvery { roomDataSource.getSongsCount() }.returns(1)

        repository.preloadSongs()

        coVerify { roomDataSource.getSongsCount()  }
        coVerify(exactly = 0) { remoteDataSource.remoteSongs() }
        coVerify(exactly = 0) { roomDataSource.insertSongs(any()) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get paged songs will check forward the call to the room data source`() = runTest {
        repository.getPagedSongs()
        coVerify { roomDataSource.getPagedSongs() }
    }
}