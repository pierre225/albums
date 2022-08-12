package com.pierre.songs.ui.splash

import com.pierre.domain.usecases.PreloadSongsUseCase
import com.pierre.songs.ui.splash.model.PreloadState
import com.pierre.songs.ui.utils.ExceptionUtils
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class PreloadSongsViewModelTest {

    @RelaxedMockK
    private lateinit var preloadSongsUseCase: PreloadSongsUseCase

    private lateinit var preloadSongsViewModel: PreloadSongsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())

        preloadSongsViewModel = PreloadSongsViewModel(preloadSongsUseCase)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `verify the first state is a loading`() {
        assert(preloadSongsViewModel.state.value == PreloadState.Loading)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify preload songs forwards the call to the use case`() = runTest {
        preloadSongsViewModel.preloadSongs()
        coVerify { preloadSongsUseCase.invoke() }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify preload songs returns success when there are no errors`() = runTest {
        coEvery { preloadSongsUseCase.invoke() } just Runs

        assert(preloadSongsViewModel.state.value == PreloadState.Loading)
        preloadSongsViewModel.preloadSongs()
        assert(preloadSongsViewModel.state.value == PreloadState.Success)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify preload songs forwards the right error if there is one`() = runTest {
        val exception = mockk<TypeCastException>(relaxed = true)
        val expectedMessage = ExceptionUtils.messageFromException(exception)
        val expectedRetry = ExceptionUtils.canRetry(exception)

        coEvery { preloadSongsUseCase.invoke() }.throws(exception)

        assert(preloadSongsViewModel.state.value == PreloadState.Loading)
        preloadSongsViewModel.preloadSongs()
        assert(preloadSongsViewModel.state.value == PreloadState.Error(expectedMessage, expectedRetry))
    }
}