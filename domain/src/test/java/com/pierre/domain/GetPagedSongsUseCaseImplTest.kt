package com.pierre.domain

import androidx.paging.PagingSource
import com.pierre.data.repository.SongsRepository
import com.pierre.data.repository.model.DataSong
import com.pierre.domain.mapper.SongsDomainMapper
import com.pierre.domain.usecases.GetPagedSongsUseCase
import com.pierre.domain.usecases.GetPagedSongsUseCaseImpl
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class GetPagedSongsUseCaseImplTest {

    @MockK
    private lateinit var repository: SongsRepository

    @RelaxedMockK
    private lateinit var mapper: SongsDomainMapper

    private lateinit var useCase: GetPagedSongsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        useCase = GetPagedSongsUseCaseImpl(
            songsRepository = repository,
            mapper = mapper,
        )
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify the songs are pulled from the repository and then mapped in domain model`() =
        runTest {
            every { repository.getPagedSongs() }.returns(mockk<PagingSource<Int, DataSong>>(relaxed = true))

            useCase.invoke().first()

            verifyOrder {
                repository.getPagedSongs()
                mapper.mapPagingDataToDomain(any())
            }
        }
}