package com.pierre.domain

import com.pierre.data.repository.SongsRepository
import com.pierre.domain.usecases.PreloadSongsUseCaseImpl
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class PreloadSongsUseCaseImplTest {

    @RelaxedMockK
    private lateinit var repository: SongsRepository

    private lateinit var useCase: PreloadSongsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        useCase = PreloadSongsUseCaseImpl(
            songsRepository = repository
        )
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify the songs are pulled from the repository`() = runTest {
        useCase.invoke()

        coVerify {
            repository.preloadSongs()
        }
    }

}