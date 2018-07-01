package com.scabher.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.scabher.marvelheroes.data.db.MarvelHeroDatabase
import com.scabher.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.scabher.marvelheroes.data.net.MarvelHeroesService
import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
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

    @Singleton
    @Provides
    fun provideMarvelHeroDatabase(context: Context): MarvelHeroDatabase =
            Room.databaseBuilder(context, MarvelHeroDatabase::class.java, "hero.db").build()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSource(marvelHeroesService: MarvelHeroesService,
                                            marvelHeroMapper: MarvelHeroMapper)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService, marvelHeroMapper)

    @Provides
    @Singleton
    fun provideLocalMarvelHeroesDataSource(marvelHeroDatabase: MarvelHeroDatabase)
            : LocalMarvelHeroesDataSource =
            LocalMarvelHeroesDataSource(marvelHeroDatabase)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                      localMarvelHeroesDataSource: LocalMarvelHeroesDataSource)
            : MarvelHeroesRepositoryImpl =
                MarvelHeroesRepositoryImpl(
                        marvelRemoteMarvelHeroesDataSource,
                        localMarvelHeroesDataSource
                )



}