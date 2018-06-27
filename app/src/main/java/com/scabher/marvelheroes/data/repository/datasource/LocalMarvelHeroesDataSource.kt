package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.data.db.MarvelHeroDatabase
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

class LocalMarvelHeroesDataSource(val marvelHeroDatabase: MarvelHeroDatabase) : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
            marvelHeroDatabase
                .getMarvelHeroDao()
                .getAllMarvelHeroes()
                .toFlowable()

}
