package com.example.lifehack_studio_test.domain.usecase

import com.example.lifehack_studio_test.domain.model.Place
import com.example.lifehack_studio_test.domain.model.PlaceInfo
import com.example.lifehack_studio_test.domain.repository.AppRepository
import com.example.lifehack_studio_test.domain.util.Resource
import javax.inject.Inject

class GetPlaceInfoUseCase @Inject constructor(
    private val repository: AppRepository,
) {
    suspend operator fun invoke(id:Int): Resource<PlaceInfo> {
        return repository.getPlaceInfo(id)
    }
}