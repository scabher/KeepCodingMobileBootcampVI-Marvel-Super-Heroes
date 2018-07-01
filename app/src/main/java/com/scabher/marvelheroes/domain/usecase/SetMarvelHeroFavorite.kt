package com.scabher.marvelheroes.domain.usecase

import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import javax.inject.Inject

class SetMarvelHeroFavorite @Inject constructor(val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl)
    : UseCase<MarvelHeroEntity>() {

    var heroName: String = ""
    var isFavorite: Boolean = false

    override fun buildCase(): Flowable<MarvelHeroEntity> =
            marvelHeroesRepositoryImpl.setHeroFavorite(heroName, isFavorite)

    fun isHeroFavorite(heroName: String) : Flowable<Boolean> =
            marvelHeroesRepositoryImpl.isHeroFavorite(heroName)

}