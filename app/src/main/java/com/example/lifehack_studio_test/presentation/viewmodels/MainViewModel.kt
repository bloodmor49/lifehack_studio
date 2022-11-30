package com.example.lifehack_studio_test.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifehack_studio_test.domain.model.Place
import com.example.lifehack_studio_test.domain.model.PlaceInfo
import com.example.lifehack_studio_test.domain.usecase.GetPlaceInfoUseCase
import com.example.lifehack_studio_test.domain.usecase.GetPlacesUseCase
import com.example.lifehack_studio_test.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
) : ViewModel() {

    private val _places = MutableStateFlow(emptyList<Place>())
    val places = _places.asStateFlow()

    private val _placeInfo = MutableStateFlow(PlaceInfo())
    val placeInfo = _placeInfo.asStateFlow()

    val currentId = MutableStateFlow(-1)

    init {
        getPlaces()
    }

    fun getPlaces() {
        viewModelScope.launch {
            when (val places = getPlacesUseCase.invoke()) {
                is Resource.Success -> {
                    _places.value = places.data ?: emptyList()
                    Log.d("ResponseData", "Success on VM Places  ${places.data}")
                }
                is Resource.Error -> {
                    Log.d("ResponseData", "Error on VM Places ${places.message}")
                    delay(1000)
                    getPlaces()
                }
            }
        }
    }

    fun getPlaceInfo() {
        viewModelScope.launch {
            when (val place = getPlaceInfoUseCase.invoke(currentId.value)) {
                is Resource.Success -> {
                    _placeInfo.value = place.data ?: PlaceInfo()
                    Log.d("ResponseData", "Success on VM Info ${place.data}")
                }
                is Resource.Error -> {
                    Log.d("ResponseData", "Error on VM Info ${place.message}")
                    delay(1000)
                    getPlaceInfo()
                }
            }
        }
    }
}