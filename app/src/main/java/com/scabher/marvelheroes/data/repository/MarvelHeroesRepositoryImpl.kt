package com.scabher.marvelheroes.data.repository

import android.util.Log
import com.scabher.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSource
import com.scabher.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.scabher.marvelheroes.domain.model.FavoriteHeroEntity
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelHeroesDataSource: LocalMarvelHeroesDataSource)
    : MarvelHeroesRepository {


    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            getLocalMarvelHeroes()
                .concatWith(getRemoteMarvelHeroes())
                .doOnNext {
                    setFavoriteHeroes(it)
                }


    override fun getFavoriteList(): Flowable<List<FavoriteHeroEntity>> =
        localMarvelHeroesDataSource.getFavoriteHeroes().toFlowable()

    override fun isHeroFavorite(heroName: String): Flowable<Boolean> =
        localMarvelHeroesDataSource.isHeroFavorite(heroName)

    override fun setFavoriteHeroes(heroes: List<MarvelHeroEntity>) {
        if (heroes.isEmpty()) return

        localMarvelHeroesDataSource.getFavoriteHeroes()
                .map {
                    val favoriteHeroList = it
                    if (!favoriteHeroList.isEmpty()) {
                        heroes.map {
                            var hero = it
                            hero.isFavorite = favoriteHeroList.find{ it.heroName == hero.name } != null
                        }
                    }
                }
    }

    override fun setHeroFavorite(heroName: String, isFavorite: Boolean): Flowable<MarvelHeroEntity> =
        localMarvelHeroesDataSource.getMarvelHero(heroName)
                .doOnNext {
                    it?.let { hero ->
                        if (isFavorite && !hero.isFavorite) {
                            val favoriteHero = FavoriteHeroEntity(0, heroName)
                            localMarvelHeroesDataSource
                                    .addFavoriteHero(favoriteHero)
                        }
                        else {
                            localMarvelHeroesDataSource
                                    .removeFavoriteHero(heroName)
                        }
                        hero.isFavorite = isFavorite
                    }
                }
                .doOnError {
                    Log.e("setHeroFavorite", it.toString())
                }


    private fun getLocalMarvelHeroes(): Flowable<List<MarvelHeroEntity>> =
            localMarvelHeroesDataSource.getMarvelHeroesList()

    private fun getRemoteMarvelHeroes(): Flowable<List<MarvelHeroEntity>> =
        remoteMarvelHeroesDataSource.getMarvelHeroesList()
                .doOnNext{
                    localMarvelHeroesDataSource.saveMarvelHeroes(it)
                }
                .doOnError {
                    Log.e("getRemoteMarvelHeroes", it.toString())
                }
}