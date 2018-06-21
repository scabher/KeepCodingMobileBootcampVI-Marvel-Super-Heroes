package com.scabher.marvelheroes.presentation

import android.app.Application
import com.scabher.marvelheroes.di.components.ApplicationComponent
import com.scabher.marvelheroes.di.components.DaggerApplicationComponent
import com.scabher.marvelheroes.di.modules.ApplicationModule

/**
 * Created by costular on 16/03/2018.
 */
class MainApp : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}