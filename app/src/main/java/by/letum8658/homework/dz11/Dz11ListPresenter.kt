package by.letum8658.homework.dz11

import by.letum8658.homework.dz6.Dz6Student
import by.letum8658.homework.dz6.Dz6StudentManager
import by.letum8658.homework.dz8.AppPrefManager

class Dz11ListPresenter {

    private var view: Dz11ListView? = null
    private lateinit var prefsManager: AppPrefManager

    fun setView(view: Dz11ListView?) {
        this.view = view
        prefsManager = view?.getPrefsManager()!!
    }

    fun getDatabase(): List<Dz6Student> = Dz6StudentManager.getStudentList()

    fun getSearchList(string: String): List<Dz6Student> = Dz6StudentManager.searchList(string)

    fun getTextForSearch(): String = prefsManager.getSearchText()

    fun saveTextForSearch(text: String) = prefsManager.saveSearchText(text)

    fun requirePrefsManager() = view?.requirePrefsManager()
}