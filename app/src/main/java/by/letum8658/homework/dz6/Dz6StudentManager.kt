package by.letum8658.homework.dz6

object Dz6StudentManager {
    private var studentsList: MutableList<Dz6Student> = mutableListOf()
    private var time: Long = -1

    init {
        time = System.currentTimeMillis()

        studentsList = mutableListOf(
            Dz6Student(
                "https://st.kp.yandex.net/images/actor/1682023.jpg",
                "Марго Робби",
                28,
                ++time
            ),
            Dz6Student(
                "https://st.kp.yandex.net/images/actor/37859.jpg",
                "Леонардо ДиКаприо",
                44,
                ++time
            ),
            Dz6Student(
                "https://st.kp.yandex.net/images/actor/25584.jpg",
                "Брэд Питт",
                55,
                ++time
            )
        )
    }

    fun getStudentList(): MutableList<Dz6Student> {
        return studentsList
    }

    fun getStudentById(id: Long): Dz6Student? {
        return studentsList.find { it.id == id }
    }

    fun deleteStudentById(student: Dz6Student) {
        studentsList.remove(student)
    }

    fun updateStudent(id: Long, newStudent: Dz6Student) {
        val oldStudent = studentsList.find { it.id == id }
        val index = studentsList.indexOf(oldStudent)
        studentsList[index] = newStudent
    }

    fun addNewStudent(student: Dz6Student) {
        student.id = ++time
        studentsList.add(student)
    }

    fun isUrl(url: String): Boolean {
        if (url.startsWith("http", true)) return true
        if (url.startsWith("https ", true)) return true
        if (url.startsWith("www", true)) return true
        return false
    }

    fun searchList(string: String): List<Dz6Student> {
        return studentsList.filter { it.name.contains(string, true) }
    }
}