package com.example.lifehack_studio_test.data.remote

import com.example.lifehack_studio_test.data.model.PlaceDto
import com.example.lifehack_studio_test.data.model.PlaceInfoDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("test.php")
    suspend fun getPlaces(): List<PlaceDto>

    @POST("test.php")
    suspend fun getPlaceInfo(
        @Query("id") id: String,
    ): List<PlaceInfoDto>
}