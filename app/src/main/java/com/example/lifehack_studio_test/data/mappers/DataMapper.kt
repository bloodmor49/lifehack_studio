package com.example.lifehack_studio_test.data.mappers

import com.example.lifehack_studio_test.data.model.PlaceDto
import com.example.lifehack_studio_test.data.model.PlaceInfoDto
import com.example.lifehack_studio_test.data.remote.ApiFactory.BASE_URL
import com.example.lifehack_studio_test.domain.model.Place
import com.example.lifehack_studio_test.domain.model.PlaceInfo

fun PlaceDto.toPlace() = Place(
    id.toInt(),
    name,
    imgUrl = "$BASE_URL/$imgUrl")

fun List<PlaceDto>.toPlaces() = map { it.toPlace() }


fun List<PlaceInfoDto>.toPlaceInfo(): PlaceInfo {
    val item = this[0]
    return PlaceInfo(
        item.id.toInt(),
        name = item.name,
        imgUrl = "$BASE_URL/${item.imgUrl}",
        item.description,
        item.lat,
        item.lon,
        item.www,
        item.phone
    )
}
