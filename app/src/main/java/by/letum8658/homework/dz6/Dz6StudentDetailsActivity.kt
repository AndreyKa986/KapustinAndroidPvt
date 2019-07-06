package by.letum8658.homework.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.letum8658.homework.R
import by.letum8658.homework.utils.loadImage
import kotlinx.android.synthetic.main.activity_dz6_student_details.*

class Dz6StudentDetailsActivity : Activity() {
    private var id: Long = -1

    companion object {
        private const val STUDENT_ID_EXTRA = "student_id_extra"

        fun getIntent(context: Context, id: Long) = Intent(context, Dz6StudentDetailsActivity::class.java)
            .putExtra(STUDENT_ID_EXTRA, id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_details)

        id = intent.getLongExtra(STUDENT_ID_EXTRA, -1)

        val student = Dz6StudentManager.getStudentById(id)

        student?.let {
            showStudent(it)
        }

        dz6delete.setOnClickListener {
            student?.let {
                deleteStudent(it)
            }
        }

        dz6edit.setOnClickListener {
            editStudent()
        }
    }

    private fun showStudent(student: Dz6Student) {
        loadImage(student.imageUrl, dz6StudentImageView)
        dz6StudentName.text = student.name
        dz6StudentAge.text = student.age.toString()
    }

    private fun deleteStudent(student: Dz6Student) {
        Dz6StudentManager.deleteStudentById(student)
        finish()
    }

    private fun editStudent() {
        startActivity(Dz6StudentEditActivity.getIntent(this, id))
        finish()
    }
}