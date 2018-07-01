package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.data.db.MarvelHeroDatabase
import com.scabher.marvelheroes.domain.model.FavoriteHeroEntity
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.intellij.lang.annotations.Flow

class LocalMarvelHeroesDataSource(val marvelHeroDatabase: MarvelHeroDatabase) : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
        marvelHeroDatabase
                .getMarvelHeroDao()
                .getAllMarvelHeroes()
                .toFlowable()

    fun getMarvelHero(heroName: String): Flowable<MarvelHeroEntity> =
        marvelHeroDatabase
                .getMarvelHeroDao()
                .getMarvelHero(heroName)
                .toFlowable()


    fun saveMarvelHeroes(heroes: List<MarvelHeroEntity>) {
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroDao().removeAndInsertMarvelHeroes(heroes)
        }
        .subscribeOn(Schedulers.io())
        .subscribe()
    }

    fun getFavoriteHeroes(): Maybe<List<FavoriteHeroEntity>> =
        marvelHeroDatabase
                .getFavoriteHeroDao()
                .getAllFavoriteHeroes()

    fun isHeroFavorite(heroName: String): Flowable<Boolean> =
            marvelHeroDatabase
                    .getFavoriteHeroDao()
                    .isHeroFavorite(heroName)

    fun addFavoriteHero(favoriteHero: FavoriteHeroEntity) =
        Observable.fromCallable {
            marvelHeroDatabase
                    .getFavoriteHeroDao()
                    .insertFavoriteHeroe(favoriteHero)
        }
        .subscribeOn(Schedulers.io())
        .subscribe()

    fun removeFavoriteHero(heroName: String) =
        Observable.fromCallable {
            marvelHeroDatabase
                    .getFavoriteHeroDao()
                    .deleteFavoriteHeroe(heroName)
        }
        .subscribeOn(Schedulers.io())
        .subscribe()
}
