package by.letum8658.homework.dz9.entity

import com.google.gson.annotations.SerializedName

data class CarResponse(

    @SerializedName("poiList")
    val poiList: List<Poi>
)