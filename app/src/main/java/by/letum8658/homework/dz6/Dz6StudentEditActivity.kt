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

        if (id < 0) {
            dz6saveButton.setOnClickListener {
                saveNewStudent()
            }
        } else {
            student?.let {

                dz6urlEditText.setText(student.imageUrl)

                dz6nameEditText.setText(student.name)

                dz6ageEditText.setText(student.age.toString())

                dz6saveButton.setOnClickListener {
                    updateStudent()
                }
            }
        }
    }

    private fun createStudent() {
        newStudent = Dz6Student()

        urlLink = dz6urlEditText.text.toString()
        newStudent.imageUrl = urlLink

        name = dz6nameEditText.text.toString()
        if (name.isNotBlank()) {
            newStudent.name = name
        }

        dz6ageEditText.text.toString().toIntOrNull()?.let {
            age = dz6ageEditText.text.toString().toInt()
        }
        newStudent.age = age
    }

    private fun updateStudent() {
        createStudent()

        if (Dz6StudentManager.isUrl(urlLink)) {
            newStudent.id = id

            Dz6StudentManager.updateStudent(id, newStudent)
            val intent = Intent(this, Dz6StudentListActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Incorrect Link", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveNewStudent() {
        createStudent()

        if (Dz6StudentManager.isUrl(urlLink)) {
            Dz6StudentManager.addNewStudent(newStudent)
            val intent = Intent(this, Dz6StudentListActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Incorrect Link", Toast.LENGTH_SHORT).show()
        }
    }
}