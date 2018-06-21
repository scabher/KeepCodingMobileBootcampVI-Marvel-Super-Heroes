package com.scabher.marvelheroes.data.net

import com.scabher.marvelheroes.data.model.MarvelHeroesResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by costular on 17/03/2018.
 */
interface MarvelHeroesService {

    @GET(".")
    fun getMarvelHeroesList(): Observable<MarvelHeroesResponse>

}