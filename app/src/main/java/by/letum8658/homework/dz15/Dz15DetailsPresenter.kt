package by.letum8658.homework.dz15

import by.letum8658.homework.dz11.Dz11DetailsView

class Dz15DetailsPresenter {

    private var view: Dz11DetailsView? = null
    private lateinit var student: Student

    fun setView(view: Dz11DetailsView?) {
        this.view = view
    }

    fun getStudent(id: String) {
        student = Dz15StudentManager.getStudentById(id)!!
    }

    fun showStudent() {
        val imageUrl = student.imageUrl
        val name = student.name
        val age = student.age.toString()
        view?.showStudent(imageUrl, name, age)
    }

    fun deleteStudent() = Dz15StudentManager.deleteStudentById(student)

    fun detach() {
        view = null
    }
}