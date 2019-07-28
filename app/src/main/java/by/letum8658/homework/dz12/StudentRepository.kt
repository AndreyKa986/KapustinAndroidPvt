package by.letum8658.homework.dz12

import io.reactivex.Observable

interface StudentRepository {

    fun getAll(): Observable<MutableList<Student>>
}