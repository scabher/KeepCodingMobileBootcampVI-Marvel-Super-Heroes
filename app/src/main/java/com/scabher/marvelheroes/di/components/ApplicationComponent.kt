package com.scabher.marvelheroes.di.components

import com.scabher.marvelheroes.di.modules.ApplicationModule
import com.scabher.marvelheroes.di.modules.DataModule
import com.scabher.marvelheroes.di.modules.NetModule
import com.scabher.marvelheroes.presentation.base.ViewModelModule
import com.scabher.marvelheroes.presentation.heroedetail.MarvelHeroeDetailActivity
import com.scabher.marvelheroes.presentation.heroeslist.HeroesListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by costular on 16/03/2018.
 */
@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetModule::class,
    DataModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(marvelListActivity: HeroesListActivity)

    fun inject(detailActivity: MarvelHeroeDetailActivity)

}
