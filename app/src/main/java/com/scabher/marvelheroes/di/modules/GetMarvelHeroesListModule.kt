package com.scabher.marvelheroes.di.modules

import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.di.scopes.PerActivity
import com.scabher.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.scabher.marvelheroes.presentation.heroeslist.HeroesListActivity
import com.scabher.marvelheroes.presentation.heroeslist.HeroesListContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class GetMarvelHeroesListModule(private val view: HeroesListActivity) {

    @Provides
    @PerActivity
    fun provideView(): HeroesListContract.View = view

    @Provides
    @PerActivity
    fun provideGetListHeroesUseCase(marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl): GetMarvelHeroesList =
            GetMarvelHeroesList(marvelHeroesRepositoryImpl)

}