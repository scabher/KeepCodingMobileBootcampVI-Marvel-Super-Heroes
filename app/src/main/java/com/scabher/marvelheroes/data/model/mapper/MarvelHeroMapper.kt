package com.scabher.marvelheroes.data.model.mapper

import com.scabher.marvelheroes.data.model.MarvelHero
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroMapper : Mapper<MarvelHero, MarvelHeroEntity> {

    override fun transform(input: MarvelHero): MarvelHeroEntity =
            MarvelHeroEntity(
                    0,
                    input.name,
                    input.photoUrl,
                    input.realName,
                    input.height,
                    input.power,
                    input.abilities,
                    input.isFavorite,
                    input.groups)
                    //getGroupsFromMarvelHero(input))

    override fun transformList(inputList: List<MarvelHero>): List<MarvelHeroEntity> =
            inputList.map { transform(it) }


    private fun getGroupsFromMarvelHero(marvelHero: MarvelHero): Array<String> =
            marvelHero.groups.replace("\\s".toRegex(), "").split(",").toTypedArray()

}