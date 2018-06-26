package com.scabher.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import com.scabher.marvelheroes.presentation.base.BaseViewModel

class MarvelHeroeDetailViewModel: BaseViewModel() {

    val heroState: MutableLiveData<MarvelHeroEntity> = MutableLiveData()

}
