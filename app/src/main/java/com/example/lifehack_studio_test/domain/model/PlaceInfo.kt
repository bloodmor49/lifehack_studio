package com.example.lifehack_studio_test.domain.model

data class PlaceInfo(
    val id: Int = EMPTY_ID,
    val name: String = EMPTY_VALUE,
    val imgUrl: String = EMPTY_VALUE,
    val description: String = EMPTY_VALUE,
    val lat: Double = EMPTY_LON_LAT,
    val lon: Double = EMPTY_LON_LAT,
    val www: String = EMPTY_VALUE,
    val phone: String = EMPTY_VALUE,
) {
    companion object {
        const val EMPTY_VALUE = ""
        const val EMPTY_ID = -1
        const val EMPTY_LON_LAT = -1.0
    }
}
