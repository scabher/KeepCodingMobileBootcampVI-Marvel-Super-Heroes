package com.scabher.marvelheroes.repository

import com.scabher.marvelheroes.data.model.MarvelHero
import com.scabher.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryTest {

    private val mockRemoteDataSource: RemoteMarvelHeroesDataSource = mock()

    private lateinit var mapper: MarvelHeroMapper
    private lateinit var marvelHeroesRepository: MarvelHeroesRepositoryImpl

    @Before
    fun setUp() {
        mapper = MarvelHeroMapper()
        marvelHeroesRepository = MarvelHeroesRepositoryImpl(mockRemoteDataSource, mapper)
    }

    @Test
    fun `repository should retrieve marvel heroes list`() {
        val heroes = listOf(MarvelHero("Iron Man"), MarvelHero("Spider-Man"))
        val flowable = Flowable.just(heroes)
        whenever(mockRemoteDataSource.getMarvelHeroesList()).thenReturn(flowable)

        val result = marvelHeroesRepository.getMarvelHeroesList()

        verify(mockRemoteDataSource).getMarvelHeroesList()

        result.test()
                .assertValue { it.size == 2 }
                .assertValue { it.first().name == heroes.first().name }
                .assertValue { it.last().name == heroes.last().name }
    }

}