package by.letum8658.homework.dz12

import by.letum8658.homework.dz11.Dz11EditView

class Dz12EditPresenter {

    private var view: Dz11EditView? = null
    private lateinit var student: Student

    fun setView(view: Dz11EditView?) {
        this.view = view
    }

    fun showStudentInformation(id: Int) {
        if (id > 0) {
            student = Dz12StudentManager.getStudentById(id)!!
            val urlLink = student.imageUrl
            val name = student.name
            val age = student.age
            view?.showStudentInformation(urlLink, name, age)
        }
    }

    fun onSaveButtonClick(id: Int) {
        val urlLink = view?.getLink()!!
        val name = view?.getName()!!
        val age = view?.getAge()
        if (checkCorrectEnterFields(urlLink, name, age)) {
            if (id < 0) {
                saveNewStudent(urlLink, name, age!!)
            } else {
                updateStudent(urlLink, name, age!!, id)
            }
        } else {
            view?.showErrorToast()
        }
    }

    private fun isUrl(url: String): Boolean {
        if (url.startsWith("http", true)) return true
        if (url.startsWith("www", true)) return true
        return false
    }

    private fun checkCorrectEnterFields(urlLink: String, name: String, age: Int?): Boolean {
        var isEnterRight = true

        if (!isUrl(urlLink)) isEnterRight = false

        if (name.isBlank()) isEnterRight = false

        if (age == null) isEnterRight = false

        return isEnterRight
    }

    private fun updateStudent(urlLink: String, name: String, age: Int, id: Int) {
        val newStudent = Student(urlLink, name, age, id)
        Dz12StudentManager.updateStudent(newStudent)
        view?.backToMainFragment()
    }

    private fun saveNewStudent(urlLink: String, name: String, age: Int) {
        val id = Dz12StudentManager.getId()
        val newStudent = Student(urlLink, name, age, id)
        Dz12StudentManager.addNewStudent(newStudent)
        view?.backToMainFragment()
    }
}