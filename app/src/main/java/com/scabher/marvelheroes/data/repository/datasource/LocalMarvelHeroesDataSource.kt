package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.data.db.FavoriteHeroDao
import com.scabher.marvelheroes.data.db.MarvelHeroDatabase
import com.scabher.marvelheroes.domain.model.FavoriteHeroEntity
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class LocalMarvelHeroesDataSource(val marvelHeroDatabase: MarvelHeroDatabase) : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            marvelHeroDatabase
                    .getMarvelHeroDao()
                    .getAllMarvelHeroes()
                    .toFlowable()

    fun saveMarvelHeroes(heroes: List<MarvelHeroEntity>) {
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroDao().removeAndInsertMarvelHeroes(heroes)
        }
        .subscribeOn(Schedulers.io())
        .subscribe()
    }

    fun getFavoriteHeroes(): Maybe<List<FavoriteHeroEntity>> =
            marvelHeroDatabase.getFavoriteHeroDao().getAllFavoriteHeroes()
}
