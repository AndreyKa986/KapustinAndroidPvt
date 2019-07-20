package by.letum8658.homework.dz11

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.letum8658.homework.R
import by.letum8658.homework.utils.loadImage
import kotlinx.android.synthetic.main.fragment_dz8_student_details.*

class Dz11DetailsFragment : Fragment(), Dz11DetailsView {

    companion object {

        private const val ID_KEY = "id_key"

        fun getInstance(id: Long): Dz11DetailsFragment {
            val fragment = Dz11DetailsFragment()
            val bundle = Bundle()
            bundle.putLong(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val presenter = Dz11DetailsPresenter()
    private var listener: Listener? = null
    private var id: Long = -1
    private lateinit var imageView: ImageView
    private lateinit var nameView: TextView
    private lateinit var ageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getLong(ID_KEY, -1) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)
        presenter.getStudent(id)

        imageView = dz8StudentImageView
        nameView = dz8StudentName
        ageView = dz8StudentAge

        presenter.showStudent()

        dz8delete.setOnClickListener {
            presenter.deleteStudent()
            listener?.onDeleteStudentClick()
        }

        dz8edit.setOnClickListener {
            listener?.onEditStudentClick(id)
        }
    }

    override fun showStudent(imageUrl: String, name: String, age: String) {
        loadImage(imageUrl, imageView)
        nameView.text = name
        ageView.text = age
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
        fun onDeleteStudentClick()
        fun onEditStudentClick(id: Long)
    }
}