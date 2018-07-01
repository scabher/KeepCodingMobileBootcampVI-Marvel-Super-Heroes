package com.scabher.marvelheroes.presentation

import android.app.Application
import com.scabher.marvelheroes.di.components.ApplicationComponent
import com.scabher.marvelheroes.di.components.DaggerApplicationComponent
import com.scabher.marvelheroes.di.modules.ApplicationModule

/**
 * Created by costular on 16/03/2018.
 */
class MainApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        // DI
        component =
                DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
    }
}