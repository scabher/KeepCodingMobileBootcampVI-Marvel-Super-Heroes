package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.scabher.marvelheroes.data.net.MarvelHeroesService
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by costular on 17/03/2018.
 */
class RemoteMarvelHeroesDataSource(private val marvelHeroesService: MarvelHeroesService,
                                   private val marvelHeroesMapper: MarvelHeroMapper):
        MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            marvelHeroesService.getMarvelHeroesList()
                    .map { it.superheroes }
                    .map { marvelHeroesMapper.transformList(it) }

}