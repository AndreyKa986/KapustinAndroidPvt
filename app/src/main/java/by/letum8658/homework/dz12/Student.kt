package by.letum8658.homework.dz12

import com.google.gson.annotations.SerializedName

data class Student(

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("age")
    val age: Int,

    @SerializedName("objectId")
    val id: String
)