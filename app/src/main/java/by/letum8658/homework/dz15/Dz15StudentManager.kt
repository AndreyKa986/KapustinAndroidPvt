package by.letum8658.homework.dz15

import by.letum8658.homework.dz12.Callback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object Dz15StudentManager {
    private const val PAGE_SIZE = 100

    private var studentsList: MutableList<Student> = mutableListOf()

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    fun getStudentList(): MutableList<Student> = studentsList

    fun loadStudentList(studentDao: StudentDao, callback: Callback) {
        disposable = repository
            .getAll(PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                studentsList = it
                studentDao
                    .insert(studentsList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        callback.returnResult()
                    }
            }, {
                studentDao
                    .get()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { list ->
                        if (list.isNotEmpty()) {
                            studentsList = list
                        }
                        callback.returnResult()
                    }
            })
    }

    fun getStudentById(id: String): Student? {
        return studentsList.find { it.id == id }
    }

    fun deleteStudentById(student: Student) {
        studentsList.remove(student)
        disposable = repository
            .deleteStudent(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun updateStudent(newStudent: Student) {
        val id = newStudent.id
        val oldStudent = studentsList.find { it.id == id }
        val index = studentsList.indexOf(oldStudent)
        studentsList[index] = newStudent
        disposable = repository
            .updateStudent(newStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun addNewStudent(student: Student) {
        studentsList.add(student)
        disposable = repository
            .saveNewStudent(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val index = studentsList.indexOf(student)
                studentsList[index] = it
            }
    }

    fun searchList(string: String): List<Student> {
        return studentsList.filter { it.name.contains(string, true) }
    }

    fun getId(): String {
        return System.currentTimeMillis().toString()
    }

    fun dispose() {
        disposable?.dispose()
    }
}