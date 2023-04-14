package com.skanderjabouzi.nbacompose.models.network

import java.io.Serializable

data class Team (
    val id : Int?,
    val name : String?,
    val wins : Int?,
    val losses : Int?,
    val imgURL : String?
) : Serializable