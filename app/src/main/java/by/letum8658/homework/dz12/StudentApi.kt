package by.letum8658.homework.dz12

import io.reactivex.Observable
import retrofit2.http.GET

interface StudentApi {

    @GET("data/student")
    fun getAllStudents(): Observable<MutableList<Student>>
}