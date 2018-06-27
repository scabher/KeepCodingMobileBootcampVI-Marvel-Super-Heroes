package com.scabher.marvelheroes.data.repository.datasource

import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

/**
 * Created by costular on 05/06/2018.
 */
class FakeMarvelHeroesDataSource : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> {
        return Flowable.just(
                arrayListOf(
                        MarvelHeroEntity("Fake", "https://i.blogs.es/30cb7a/blackpanther5/450_1000.jpg" ),
                        MarvelHeroEntity("Fake 2", "https://thefreakchoice.com/2833-home_default/cojin-arrow-flechas-verde-.jpg")
                )
        )
    }

}