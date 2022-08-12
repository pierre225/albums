package com.pierre.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pierre.data.repository.SongsRepository
import com.pierre.domain.mapper.SongsDomainMapper
import com.pierre.domain.model.DomainSong
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetPagedSongsUseCase : () -> Flow<PagingData<DomainSong>>

internal class GetPagedSongsUseCaseImpl(
    private val songsRepository: SongsRepository,
    private val mapper: SongsDomainMapper
): GetPagedSongsUseCase {

    override fun invoke(): Flow<PagingData<DomainSong>> =
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = INITIAL_LOADING_SIZE,
                enablePlaceholders = false
            )
        ) { songsRepository.getPagedSongs() }
            .flow
            .map {
                mapper.mapPagingDataToDomain(it)
            }

    companion object {
        private const val PAGE_SIZE = 25
        private const val INITIAL_LOADING_SIZE = 100
    }
}