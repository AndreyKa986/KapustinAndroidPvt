package by.letum8658.homework.dz12

import by.letum8658.homework.dz8.AppPrefManager

interface Dz12ListView {

    fun getPrefsManager(): AppPrefManager
    fun requirePrefsManager(): AppPrefManager
    fun updateAdapter(list: List<Student>)
}