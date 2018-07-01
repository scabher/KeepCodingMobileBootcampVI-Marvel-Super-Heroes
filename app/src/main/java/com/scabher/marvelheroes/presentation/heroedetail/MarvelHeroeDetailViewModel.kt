package com.scabher.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.scabher.marvelheroes.domain.model.FavoriteHeroEntity
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import com.scabher.marvelheroes.presentation.base.BaseViewModel
import com.scabher.marvelheroes.domain.usecase.SetMarvelHeroFavorite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MarvelHeroeDetailViewModel @Inject constructor (private val setMarvelHeroFavorite: SetMarvelHeroFavorite)
    : BaseViewModel() {

    val heroState: MutableLiveData<MarvelHeroEntity> = MutableLiveData()

    fun setHeroFavorite(heroName: String, isFavorite: Boolean) {
        setMarvelHeroFavorite.heroName = heroName
        setMarvelHeroFavorite.isFavorite = isFavorite

        setMarvelHeroFavorite.execute(
                onSuccess = { heroUpdated ->
                    heroState.value = heroUpdated
                    Log.d("setHeroFavorite", "Hero favorite updated successfully")
                },
                onError = {
                    Log.d("setHeroFavorite", "Error updating hero favorite:" + it.message)
                })
    }

    fun isHeroFavorite(heroName: String) {
        setMarvelHeroFavorite.isHeroFavorite(heroName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { isFavorite ->
                            val tempHero = MarvelHeroEntity(id = 0, isFavorite = isFavorite)
                            heroState.value = tempHero
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
