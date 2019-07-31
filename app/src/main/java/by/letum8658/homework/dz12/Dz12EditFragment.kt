package by.letum8658.homework.dz12

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.letum8658.homework.R
import by.letum8658.homework.dz11.Dz11EditView
import kotlinx.android.synthetic.main.fragment_dz8_student_edit.*

class Dz12EditFragment : Fragment(), Dz11EditView {

    companion object {

        private const val ID_KEY = "id_key"

        fun getInstance(id: String): Dz12EditFragment {
            val fragment = Dz12EditFragment()
            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val presenter = Dz12EditPresenter()
    private var listener: Listener? = null
    private var idStudent: String = " "
    private lateinit var imageUrlText: EditText
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idStudent = arguments?.getString(ID_KEY, " ") ?: " "
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)

        imageUrlText = dz8urlEditText
        nameText = dz8nameEditText
        ageText = dz8ageEditText

        presenter.showStudentInformation(idStudent)

        dz8saveButton.setOnClickListener {
            presenter.onSaveButtonClick(idStudent)
        }
    }

    override fun showStudentInformation(urlLink: String, name: String, age: Int) {
        imageUrlText.setText(urlLink)
        nameText.setText(name)
        ageText.setText(age.toString())
    }

    override fun getLink(): String = imageUrlText.text.toString()

    override fun getName(): String = nameText.text.toString()

    override fun getAge(): Int? = ageText.text.toString().toIntOrNull()

    override fun backToMainFragment() {
        listener?.onSaveStudentClick()
    }

    override fun showErrorToast() {
        Toast.makeText(context, getText(R.string.incorrect_information), Toast.LENGTH_SHORT).show()
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
        presenter.detach()
    }

    interface Listener {
        fun onSaveStudentClick()
    }
}