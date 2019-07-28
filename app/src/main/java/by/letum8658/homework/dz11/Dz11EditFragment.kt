package by.letum8658.homework.dz11

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.letum8658.homework.R
import kotlinx.android.synthetic.main.fragment_dz8_student_edit.*

class Dz11EditFragment : Fragment(), Dz11EditView {

    companion object {

        private const val ID_KEY = "id_key"

        fun getInstance(id: Long): Dz11EditFragment {
            val fragment = Dz11EditFragment()
            val bundle = Bundle()
            bundle.putLong(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val presenter = Dz11EditPresenter()
    private var listener: Listener? = null
    private var id: Long = -1
    private lateinit var imageUrlText: EditText
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getLong(ID_KEY, -1) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)

        imageUrlText = dz8urlEditText
        nameText = dz8nameEditText
        ageText = dz8ageEditText

        presenter.showStudentInformation(id)

        dz8saveButton.setOnClickListener {
            presenter.onSaveButtonClick(id)
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
    }

    interface Listener {
        fun onSaveStudentClick()
    }
}