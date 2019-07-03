package by.letum8658.homework.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.letum8658.homework.R
import kotlinx.android.synthetic.main.activity_dz6_student_edit.*

class Dz6StudentEditActivity : Activity() {
    private lateinit var newStudent: Dz6Student
    private var id: Long = -1
    private var urlLink: String = " "
    private var name: String = " "
    private var age: Int = 0
    private var isEnterRight: Boolean = true

    companion object {
        private const val STUDENT_ID_EXTRA = "student_id_extra"

        fun getIntent(context: Context, id: Long) = Intent(context, Dz6StudentEditActivity::class.java)
            .putExtra(STUDENT_ID_EXTRA, id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_edit)

        id = intent.getLongExtra(STUDENT_ID_EXTRA, -1)

        val student = Dz6StudentManager.getStudentById(id)

        student?.let {

            dz6urlEditText.setText(it.imageUrl)

            dz6nameEditText.setText(it.name)

            dz6ageEditText.setText(it.age.toString())
        }

        dz6saveButton.setOnClickListener {
            checkCorrectEnterFields()
            if (id < 0) {
                saveNewStudent()
            } else {
                updateStudent()
            }
        }
    }

    private fun updateStudent() {
        if (isEnterRight) {
            newStudent = Dz6Student(urlLink, name, age, id)
            Dz6StudentManager.updateStudent(newStudent)
            val intent = Intent(this, Dz6StudentListActivity::class.java)
            startActivity(intent)
        } else {
            isEnterRight = true
        }
        finish()
    }

    private fun saveNewStudent() {
        if (isEnterRight) {
            id = Dz6StudentManager.getId()
            newStudent = Dz6Student(urlLink, name, age, id)
            Dz6StudentManager.addNewStudent(newStudent)
            val intent = Intent(this, Dz6StudentListActivity::class.java)
            startActivity(intent)
        } else {
            isEnterRight = true
        }
        finish()
    }

    private fun isUrl(url: String): Boolean {
        if (url.startsWith("http", true)) return true
        if (url.startsWith("https ", true)) return true
        if (url.startsWith("www", true)) return true
        return false
    }

    private fun checkCorrectEnterFields() {
        if (isUrl(dz6urlEditText.text.toString())) {
            urlLink = dz6urlEditText.text.toString()
        } else {
            isEnterRight = false
            Toast.makeText(this, getString(R.string.incorrect_link), Toast.LENGTH_SHORT).show()
        }

        if (dz6nameEditText.text.toString().isNotBlank()) {
            name = dz6nameEditText.text.toString()
        } else {
            isEnterRight = false
            Toast.makeText(this, getString(R.string.empty_name_field), Toast.LENGTH_SHORT).show()
        }

        if (dz6ageEditText.text.toString().toIntOrNull() != null) {
            age = dz6ageEditText.text.toString().toInt()
        } else {
            isEnterRight = false
            Toast.makeText(this, getString(R.string.incorrect_number), Toast.LENGTH_SHORT).show()
        }
    }
}