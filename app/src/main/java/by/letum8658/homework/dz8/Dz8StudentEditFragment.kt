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
    private var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getLong(ID_KEY, -1) ?: -1
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

            val urlLink = dz8urlEditText.text.toString()
            val name = dz8nameEditText.text.toString()
            val age = dz8ageEditText.text.toString().toIntOrNull()

            if (checkCorrectEnterFields(urlLink, name, age)) {
                if (id < 0) {
                    saveNewStudent(urlLink, name, age!!)
                } else {
                    updateStudent(urlLink, name, age!!)
                }
            }
        }
    }

    private fun updateStudent(urlLink: String, name: String, age: Int) {
            newStudent = Dz6Student(urlLink, name, age, id)
            Dz6StudentManager.updateStudent(newStudent)
            listener?.onSaveStudentClick()
    }

    private fun saveNewStudent(urlLink: String, name: String, age: Int) {
            id = Dz6StudentManager.getId()
            newStudent = Dz6Student(urlLink, name, age, id)
            Dz6StudentManager.addNewStudent(newStudent)
            listener?.onSaveStudentClick()
    }

    private fun isUrl(url: String): Boolean {
        if (url.startsWith("http", true)) return true
        if (url.startsWith("www", true)) return true
        return false
    }

    private fun checkCorrectEnterFields(urlLink: String, name: String, age: Int?): Boolean {
        var isEnterRight = true

        if (!isUrl(urlLink)) {
            isEnterRight = false
            Toast.makeText(context, getString(R.string.incorrect_link), Toast.LENGTH_SHORT).show()
        }

        if (name.isBlank()) {
            isEnterRight = false
            Toast.makeText(context, getString(R.string.empty_name_field), Toast.LENGTH_SHORT).show()
        }

        if (age == null) {
            isEnterRight = false
            Toast.makeText(context, getString(R.string.incorrect_number), Toast.LENGTH_SHORT).show()
        }

        return isEnterRight
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
        fun onSaveStudentClick()
    }
}