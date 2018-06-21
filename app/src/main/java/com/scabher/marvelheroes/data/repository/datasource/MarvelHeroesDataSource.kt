package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.data.model.MarvelHero
import io.reactivex.Observable

/**
 * Created by costular on 17/03/2018.
 */
interface MarvelHeroesDataSource {

    fun getMarvelHeroesList(): Observable<List<MarvelHero>>

}