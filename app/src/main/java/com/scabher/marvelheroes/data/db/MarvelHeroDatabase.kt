package com.scabher.marvelheroes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity

@Database(entities = [MarvelHeroEntity::class], version = 1)
abstract class MarvelHeroDatabase: RoomDatabase() {

    abstract fun getMarvelHeroDao(): MarvelHeroDao

    abstract fun getFavoriteHeroDao(): FavoriteHeroDao
}