package com.skanderjabouzi.nbacompose.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamDetailsEntity(
    @PrimaryKey
    var id: Int,
    var region: String,
    var name: String,
    var abbrev: String,
    var pop: Double,
    var imgURL: String
)