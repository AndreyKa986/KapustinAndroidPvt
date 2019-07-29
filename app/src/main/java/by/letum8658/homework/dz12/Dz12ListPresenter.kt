package by.letum8658.homework.dz12

import by.letum8658.homework.dz8.AppPrefManager

class Dz12ListPresenter {

    private var view: Dz12ListView? = null
    private lateinit var prefsManager: AppPrefManager

    fun setView(view: Dz12ListView?) {
        this.view = view
        prefsManager = view?.getPrefsManager()!!
    }

    fun getDatabase(): List<Student> {
        val studentList = Dz12StudentManager.getStudentList()
        if (studentList.isEmpty()) {
            view?.progressBarOn()
            Dz12StudentManager.loadStudentList(object : Callback() {
                override fun returnResult() {
                    view?.progressBarOff()
                    view?.updateDatabase()
                }
            })
        }
        return studentList
    }

    fun getSearchList(string: String): List<Student> = Dz12StudentManager.searchList(string)

    fun getTextForSearch(): String = prefsManager.getSearchText()

    fun saveTextForSearch(text: String) = prefsManager.saveSearchText(text)

    fun requirePrefsManager() = view?.requirePrefsManager()
}