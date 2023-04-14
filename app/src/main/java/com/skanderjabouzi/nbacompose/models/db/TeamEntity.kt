package com.skanderjabouzi.nbacompose.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity (
    @PrimaryKey
    val id : Int,
    val name : String,
    val wins : Int,
    val losses : Int,
    val imgURL : String
)