package com.scabher.marvelheroes.domain.usecase

import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by costular on 17/03/2018.
 */
class GetMarvelHeroesList @Inject constructor(val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl)
    : UseCase<List<MarvelHeroEntity>>() {

    override fun buildCase(): Observable<List<MarvelHeroEntity>> =
            marvelHeroesRepositoryImpl.getMarvelHeroesList()

}