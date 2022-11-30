package com.example.lifehack_studio_test.data.model

import com.google.gson.annotations.SerializedName

data class PlaceInfoDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val imgUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("www")
    val www: String,
    @SerializedName("phone")
    val phone: String,
)
