package com.scabher.marvelheroes.presentation.heroeslist

import com.scabher.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Created by costular on 17/03/2018.
 */
interface HeroesListContract {

    interface View {

        fun showLoading(isLoading: Boolean)

        fun showHeroesList(heroes: List<MarvelHeroEntity>)

        fun showError(message: String)

        fun showError(messageRes: Int)

    }
}