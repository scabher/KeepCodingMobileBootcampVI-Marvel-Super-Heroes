package com.scabher.marvelheroes.di.components

import android.content.Context
import com.scabher.marvelheroes.data.net.MarvelHeroesService
import com.scabher.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.scabher.marvelheroes.di.modules.ApplicationModule
import com.scabher.marvelheroes.di.modules.DataModule
import com.scabher.marvelheroes.di.modules.NetModule
import com.scabher.marvelheroes.presentation.util.Navigator
import dagger.Component
import javax.inject.Singleton

/**
 * Created by costular on 16/03/2018.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, DataModule::class])
interface ApplicationComponent {

    fun getContext(): Context
    fun getRepository(): MarvelHeroesRepositoryImpl
    fun getHeroService(): MarvelHeroesService
    fun getNavigator(): Navigator

}