package by.letum8658.homework.dz12

import by.letum8658.homework.dz11.Dz11DetailsView
import by.letum8658.homework.dz6.Dz6Student
import by.letum8658.homework.dz6.Dz6StudentManager

class Dz12DetailsPresenter {

    private var view: Dz11DetailsView? = null
    private lateinit var student: Dz6Student

    fun setView(view: Dz11DetailsView?) {
        this.view = view
    }

    fun getStudent(id: Int) {
        student = Dz6StudentManager.getStudentById(id.toLong())!!
    }

    fun showStudent() {
        val imageUrl = student.imageUrl
        val name = student.name
        val age = student.age.toString()
        view?.showStudent(imageUrl, name, age)
    }

    fun deleteStudent() {
        Dz6StudentManager.deleteStudentById(student)
    }
}