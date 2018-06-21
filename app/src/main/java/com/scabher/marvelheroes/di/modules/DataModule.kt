package com.scabher.marvelheroes.di.modules

import com.scabher.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.scabher.marvelheroes.data.net.MarvelHeroesService
import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSoruce(marvelHeroesService: MarvelHeroesService)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(
            marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
            marvelHeroMapper: MarvelHeroMapper): MarvelHeroesRepositoryImpl =
            MarvelHeroesRepositoryImpl(marvelRemoteMarvelHeroesDataSource, marvelHeroMapper)

}