package by.letum8658.homework.dz12

import io.reactivex.Completable
import io.reactivex.Observable

interface StudentRepository {

    fun getAll(pageSize: Int): Observable<MutableList<Student>>
    fun saveNewStudent(student: Student): Observable<Student>
    fun updateStudent(student: Student): Completable
    fun deleteStudent(student: Student): Completable
}