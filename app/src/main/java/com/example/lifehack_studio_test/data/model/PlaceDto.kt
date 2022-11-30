package com.example.lifehack_studio_test.data.model

import com.google.gson.annotations.SerializedName

data class PlaceDto(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("img")
    val imgUrl: String,
)
