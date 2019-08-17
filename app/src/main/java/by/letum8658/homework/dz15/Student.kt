package by.letum8658.homework.dz15

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val DZ15_TABLET_NAME = "Dz15_Students"

@Entity(tableName = DZ15_TABLET_NAME)
data class Student(

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("age")
    val age: Int,

    @PrimaryKey
    @SerializedName("objectId")
    val id: String
)