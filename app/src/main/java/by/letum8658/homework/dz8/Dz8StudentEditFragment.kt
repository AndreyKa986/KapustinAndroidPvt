package by.letum8658.homework.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.letum8658.homework.R
import by.letum8658.homework.dz6.Dz6Student
import by.letum8658.homework.dz6.Dz6StudentManager
import kotlinx.android.synthetic.main.fragment_dz8_student_edit.*

class Dz8StudentEditFragment : Fragment() {

    companion object {

        private const val ID_KEY = "id_key"

        fun getInstance(id: Long): Dz8StudentEditFragment {
            val fragment = Dz8StudentEditFragment()
            val bundle = Bundle()
            bundle.putLong(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var listener: Listener? = null
    private lateinit var newStudent: Dz6Student
    private var urlLink: String = " "
    private var name: String = " "
    private var age: Int = 0
    private var isEnterRight: Boolean = true
    private var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getLong(ID_KEY, -1) ?: -1
        retainInstance
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val student = Dz6StudentManager.getStudentById(id)

        student?.let {

            dz8urlEditText.setText(it.imageUrl)

            dz8nameEditText.setText(it.name)

            dz8ageEditText.setText(it.age.toString())
        }

        dz8saveButton.setOnClickListener {
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
            listener?.onSaveOrDeleteStudentClick()
        } else {
            isEnterRight = true
        }
    }

    private fun saveNewStudent() {
        if (isEnterRight) {
            id = Dz6StudentManager.getId()
            newStudent = Dz6Student(urlLink, name, age, id)
            Dz6StudentManager.addNewStudent(newStudent)
            listener?.onSaveOrDeleteStudentClick()
        } else {
            isEnterRight = true
        }
    }

    private fun isUrl(url: String): Boolean {
        if (url.startsWith("http", true)) return true
        if (url.startsWith("www", true)) return true
        return false
    }

    private fun checkCorrectEnterFields() {
        if (isUrl(dz8urlEditText.text.toString())) {
            urlLink = dz8urlEditText.text.toString()
        } else {
            isEnterRight = false
            Toast.makeText(context, getString(R.string.incorrect_link), Toast.LENGTH_SHORT).show()
        }

        if (dz8nameEditText.text.toString().isNotBlank()) {
            name = dz8nameEditText.text.toString()
        } else {
            isEnterRight = false
            Toast.makeText(context, getString(R.string.empty_name_field), Toast.LENGTH_SHORT).show()
        }

        if (dz8ageEditText.text.toString().toIntOrNull() != null) {
            age = dz8ageEditText.text.toString().toInt()
        } else {
            isEnterRight = false
            Toast.makeText(context, getString(R.string.incorrect_number), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun onSaveOrDeleteStudentClick()
    }
}