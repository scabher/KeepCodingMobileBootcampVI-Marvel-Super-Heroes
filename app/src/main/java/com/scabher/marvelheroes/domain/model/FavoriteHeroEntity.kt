package com.scabher.marvelheroes.domain.model

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "favoriteheroes")
@SuppressLint("ParcelCreator")
@Parcelize
data class FavoriteHeroEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val heroId: Long
) : Parcelable

