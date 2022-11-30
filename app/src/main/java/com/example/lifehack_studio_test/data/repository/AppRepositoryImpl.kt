package com.example.lifehack_studio_test.data.repository

import android.util.Log
import com.example.lifehack_studio_test.data.mappers.toPlaceInfo
import com.example.lifehack_studio_test.data.mappers.toPlaces
import com.example.lifehack_studio_test.data.remote.ApiFactory
import com.example.lifehack_studio_test.domain.model.Place
import com.example.lifehack_studio_test.domain.model.PlaceInfo
import com.example.lifehack_studio_test.domain.repository.AppRepository
import com.example.lifehack_studio_test.domain.util.Resource
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiFactory: ApiFactory,
) : AppRepository {

    override suspend fun getPlaces(): Resource<List<Place>> {
        return try {
            Resource.Success(data = apiFactory.retrofit.getPlaces().toPlaces())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message
                ?: "Неизвестная ошибка в методе getPlaces.")
        }
    }

    override suspend fun getPlaceInfo(id: Int): Resource<PlaceInfo> {
        return try {
            Resource.Success(data = apiFactory.retrofit.getPlaceInfo(id.toString()).toPlaceInfo())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message
                ?: "Неизвестная ошибка в методе getPlaceInfo.")
        }
    }

}