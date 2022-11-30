package com.example.lifehack_studio_test.domain.usecase

import com.example.lifehack_studio_test.domain.model.Place
import com.example.lifehack_studio_test.domain.repository.AppRepository
import com.example.lifehack_studio_test.domain.util.Resource
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(
    private val repository: AppRepository,
) {
    suspend operator fun invoke(): Resource<List<Place>> {
        return repository.getPlaces()
    }
}