package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.data.model.MarvelHero
import com.scabher.marvelheroes.data.net.MarvelHeroesService
import io.reactivex.Observable

/**
 * Created by costular on 17/03/2018.
 */
class RemoteMarvelHeroesDataSource(private val marvelHeroesService: MarvelHeroesService):
        MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Observable<List<MarvelHero>> =
            marvelHeroesService.getMarvelHeroesList().map { it.superheroes }

}