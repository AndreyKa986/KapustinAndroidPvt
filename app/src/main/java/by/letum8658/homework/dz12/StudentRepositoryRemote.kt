package by.letum8658.homework.dz12

import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun getAll(): Observable<MutableList<Student>> {
        return api.getAllStudents()
    }
}