package com.scabher.marvelheroes.data.repository

import com.scabher.marvelheroes.domain.model.FavoriteHeroEntity
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by costular on 17/03/2018.
 */
interface MarvelHeroesRepository {

    fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>>

    fun getFavoriteList(): Flowable<List<FavoriteHeroEntity>>

    fun isHeroFavorite(heroName: String): Flowable<Boolean>

    fun setFavoriteHeroes(heroes: List<MarvelHeroEntity>)

    fun setHeroFavorite(heroName: String, isFavorite: Boolean): Flowable<MarvelHeroEntity>

}