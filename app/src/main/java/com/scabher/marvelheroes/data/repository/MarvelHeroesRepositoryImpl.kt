package com.scabher.marvelheroes.data.repository

import com.scabher.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.scabher.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource)
    : MarvelHeroesRepository {


    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> {
        return getLocalMarvelHeroes()
                .concatWith(getRemoteMarvelHeroes())
                .doOnNext {
                    setFavoriteHeroes(it)
                }
    }

    private fun getLocalMarvelHeroes(): Flowable<List<MarvelHeroEntity>> =
            localMarvelHeroesDataSource.getMarvelHeroesList()

    private fun getRemoteMarvelHeroes(): Flowable<List<MarvelHeroEntity>> =
        remoteMarvelHeroesDataSource.getMarvelHeroesList()
                .doOnNext{ localMarvelHeroesDataSource.saveMarvelHeroes(it) }

    private fun setFavoriteHeroes(heroes: List<MarvelHeroEntity>) {
        heroes.map {
            // TODO: Set hero.isFavorite
        }
    }
}