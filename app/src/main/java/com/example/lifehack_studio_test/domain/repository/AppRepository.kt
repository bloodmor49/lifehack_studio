package com.example.lifehack_studio_test.domain.repository

import com.example.lifehack_studio_test.domain.model.Place
import com.example.lifehack_studio_test.domain.model.PlaceInfo
import com.example.lifehack_studio_test.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getPlaces(): Resource<List<Place>>
    suspend fun getPlaceInfo(id:Int): Resource<PlaceInfo>
}