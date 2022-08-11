package com.pierre.songs.ui.di

import com.pierre.songs.ui.mapper.SongsUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    @Singleton
    fun uiMapper() = SongsUiMapper()

}
