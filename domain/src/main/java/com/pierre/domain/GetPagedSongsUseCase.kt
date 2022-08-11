package com.pierre.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.pierre.data.repository.SongsRepository
import com.pierre.domain.mapper.SongsDomainMapper
import com.pierre.domain.model.DomainSong
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//todo rename getPAgedsongs
class GetSongsUseCase(
    private val songsRepository: SongsRepository,
    private val mapper: SongsDomainMapper
) {

    fun invoke(): Flow<PagingData<DomainSong>> =
        Pager(
            PagingConfig(
                pageSize = 25,
                initialLoadSize = 25,
                enablePlaceholders = false
            )
        ) { songsRepository.getPagedSongs() }
            .flow
            .map {
                mapper.mapPagingDataToDomain(it)
            }
            //.map { pagingData -> pagingData.map { dataSong -> songsDomainMapper.toDomain(dataSong) } }

}