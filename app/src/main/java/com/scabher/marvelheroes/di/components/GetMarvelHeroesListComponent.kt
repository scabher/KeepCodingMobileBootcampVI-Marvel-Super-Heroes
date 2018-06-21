package com.scabher.marvelheroes.di.components

import com.scabher.marvelheroes.di.modules.GetMarvelHeroesListModule
import com.scabher.marvelheroes.di.scopes.PerActivity
import com.scabher.marvelheroes.presentation.heroeslist.HeroesListActivity
import dagger.Component

/**
 * Created by costular on 17/03/2018.
 */
@PerActivity
@Component(modules = [GetMarvelHeroesListModule::class], dependencies = [ApplicationComponent::class])
interface GetMarvelHeroesListComponent {

    fun inject(marvelListActivity: HeroesListActivity)

}