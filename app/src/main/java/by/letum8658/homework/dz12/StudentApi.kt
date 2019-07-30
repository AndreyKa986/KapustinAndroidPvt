package by.letum8658.homework.dz12

import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.DELETE

interface StudentApi {

    @GET("data/student")
    fun getAllStudents(
        @Query("pageSize") pageSize: Int
    ): Observable<MutableList<Student>>

    @POST("data/student")
    fun saveStudent(
        @Body student: Student
    ): Observable<Student>

    @PUT("data/student/{objectId}")
    fun updateStudent(
        @Path("objectId") id: String,
        @Body student: Student
    ): Completable

    @DELETE("data/student/{objectId}")
    fun deleteStudent(
        @Path("objectId") id: String
    ): Completable
}