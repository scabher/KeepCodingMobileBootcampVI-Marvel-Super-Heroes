package com.scabher.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import com.scabher.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.scabher.marvelheroes.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(private val getMarvelHeroesList: GetMarvelHeroesList)
    : BaseViewModel() {

    val heroListState: MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState: MutableLiveData<Boolean> = MutableLiveData()

    fun loadMarvelHeroes() {
        getMarvelHeroesList.buildCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate { isLoadingState.postValue(false) }
                .subscribeBy(
                        onNext = {
                            heroListState.value = it
                        },
                        onError = {
                            Log.d("HeroesListViewModel", it.toString())
                        },
                        onComplete = {
                            //settingsManager.firstLoad = false
                        }
                )
                .addTo(compositeDisposable)
    }
}