package com.pierre.songs.ui.songslist

import androidx.paging.PagingData
import com.pierre.domain.model.DomainSong
import com.pierre.domain.usecases.GetPagedSongsUseCase
import com.pierre.songs.ui.songslist.mapper.SongsUiMapper
import com.pierre.songs.ui.songslist.model.UiSongsState
import com.pierre.songs.ui.utils.ExceptionUtils
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class PagedSongsViewModelTest {

    @RelaxedMockK
    private lateinit var mapper: SongsUiMapper

    @RelaxedMockK
    private lateinit var pagedSongsUseCase: GetPagedSongsUseCase

    private lateinit var pagedSongsViewModel: PagedSongsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())

        pagedSongsViewModel = PagedSongsViewModel(
            mapper = mapper,
            getPagedSongsUseCase = pagedSongsUseCase
        )
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `verify the first state is a loading`() {
        assert(pagedSongsViewModel.state.value == UiSongsState.UiLoadingState)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify fetch paged songs forwards the call to the use case`() = runTest {
        pagedSongsViewModel.fetchPagedSongs()
        coVerify { pagedSongsUseCase.invoke() }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify fetch paged songs maps the right paging data and forwards the right state when there are no errors`() = runTest {
        val flow = mockk<Flow<PagingData<DomainSong>>>(relaxed = true)
        coEvery { pagedSongsUseCase.invoke() }.returns(flow)

        assert(pagedSongsViewModel.state.value == UiSongsState.UiLoadingState)
        pagedSongsViewModel.fetchPagedSongs()

        flow.map {
            verify { mapper.mapPagingDataToDomain(it) }
            // Verify the data in our state is the mapped data
            assert(pagedSongsViewModel.state.value == UiSongsState.UiSongsResultsState(mapper.mapPagingDataToDomain(it)))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify get paged songs forwards the right error if there is one`() = runTest {
        val exception = mockk<IOException>(relaxed = true)
        val expectedMessage = ExceptionUtils.messageFromException(exception)
        val expectedRetry = ExceptionUtils.canRetry(exception)

        coEvery { pagedSongsUseCase.invoke() }.throws(exception)

        assert(pagedSongsViewModel.state.value == UiSongsState.UiLoadingState)
        pagedSongsViewModel.fetchPagedSongs()
        assert(pagedSongsViewModel.state.value == UiSongsState.UiErrorState(expectedMessage, expectedRetry))
    }
}