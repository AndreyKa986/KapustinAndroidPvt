package by.letum8658.homework.dz11

interface Dz11EditView {

    fun showStudentInformation(urlLink: String, name: String, age: Int)
    fun getLink(): String
    fun getName(): String
    fun getAge(): Int?
    fun showErrorToast()
    fun backToMainFragment()
}