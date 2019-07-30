package by.letum8658.homework.dz12

import io.reactivex.Completable
import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun getAll(pageSize: Int): Observable<MutableList<Student>> {
        return api.getAllStudents(pageSize)
    }

    override fun saveNewStudent(student: Student): Observable<Student> {
        return api.saveStudent(student)
    }

    override fun updateStudent(student: Student): Completable {
        return api.updateStudent(student.id, student)
    }

    override fun deleteStudent(student: Student): Completable {
        return api.deleteStudent(student.id)
    }
}