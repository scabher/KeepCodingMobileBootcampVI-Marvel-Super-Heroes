package com.scabher.marvelheroes.data.db

import android.arch.persistence.room.*
import com.scabher.marvelheroes.domain.model.FavoriteHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
abstract class FavoriteHeroDao {
    @Query("SELECT COUNT(1) > 0 FROM favoriteheroes WHERE heroName = :heroName ")
    abstract fun isHeroFavorite(heroName: String): Flowable<Boolean>

    @Query("SELECT * FROM favoriteheroes")
    abstract fun getAllFavoriteHeroes(): Maybe<List<FavoriteHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllFavoriteHeroes(heroes: List<FavoriteHeroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavoriteHeroe(favoriteHero: FavoriteHeroEntity)

    @Query("DELETE FROM favoriteheroes")
    abstract fun deleteAllFavoriteHeroes()

    @Query("DELETE FROM favoriteheroes WHERE heroName = :heroName")
    abstract fun deleteFavoriteHeroe(heroName: String)
}