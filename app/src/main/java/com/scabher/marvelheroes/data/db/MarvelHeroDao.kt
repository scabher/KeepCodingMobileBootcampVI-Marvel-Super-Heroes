package com.scabher.marvelheroes.data.db

import android.arch.persistence.room.*
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Maybe

@Dao
abstract class MarvelHeroDao {

    @Query("SELECT * FROM marvelheroes")
    abstract fun getAllMarvelHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllMarvelHeroes(heroes: List<MarvelHeroEntity>)

    @Query("DELETE FROM marvelheroes")
    abstract fun deleteAllMarvelHeroes()

    @Transaction
    open fun removeAndInsertMarvelHeroes(heroes: List<MarvelHeroEntity>) {
        deleteAllMarvelHeroes()
        insertAllMarvelHeroes(heroes)
    }

}
