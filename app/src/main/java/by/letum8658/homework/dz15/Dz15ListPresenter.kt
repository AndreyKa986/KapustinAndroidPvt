package by.letum8658.homework.dz15

import by.letum8658.homework.dz12.Callback
import by.letum8658.homework.dz12.Dz12ListView
import by.letum8658.homework.dz8.AppPrefManager

class Dz15ListPresenter {

    private var view: Dz12ListView? = null
    private lateinit var prefsManager: AppPrefManager

    fun setView(view: Dz12ListView?) {
        this.view = view
        prefsManager = view?.getPrefsManager()!!
    }

    fun getDatabase(studentDao: StudentDao): List<Student> {
        val studentList = Dz15StudentManager.getStudentList()
        if (studentList.isEmpty()) {
            view?.progressBarOn()
            Dz15StudentManager.loadStudentList(studentDao, object : Callback() {
                override fun returnResult() {
                    view?.progressBarOff()
                    view?.updateDatabase()
                }
            })
        }
        return studentList
    }

    fun getSearchList(string: String): List<Student> = Dz15StudentManager.searchList(string)

    fun getTextForSearch(): String = prefsManager.getSearchText()

    fun saveTextForSearch(text: String) = prefsManager.saveSearchText(text)

    fun requirePrefsManager() = view?.requirePrefsManager()

    fun detach() {
        Dz15StudentManager.dispose()
        view = null
    }
}