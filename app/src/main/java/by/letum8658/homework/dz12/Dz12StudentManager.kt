package by.letum8658.homework.dz12

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object Dz12StudentManager {
    private var studentsList: MutableList<Student> = mutableListOf()

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    fun getStudentList(): MutableList<Student> = studentsList

    fun loadStudentList(callback: Callback) {
        disposable = repository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                studentsList = it
                callback.returnResult()
            }
    }

    fun getStudentById(id: Int): Student? {
        return studentsList.find { it.id == id }
    }

    fun deleteStudentById(student: Student) {
        studentsList.remove(student)
    }

    fun updateStudent(newStudent: Student) {
        val id = newStudent.id
        val oldStudent = studentsList.find { it.id == id }
        val index = studentsList.indexOf(oldStudent)
        studentsList[index] = newStudent
    }

    fun addNewStudent(student: Student) {
        studentsList.add(student)
    }

    fun searchList(string: String): List<Student> {
        return studentsList.filter { it.name.contains(string, true) }
    }

    fun getId(): Int {
        return System.currentTimeMillis().toInt()
    }
}