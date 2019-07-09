package by.letum8658.homework.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.letum8658.homework.R
import by.letum8658.homework.dz6.Dz6Student
import by.letum8658.homework.dz6.Dz6StudentManager
import by.letum8658.homework.utils.loadImage
import kotlinx.android.synthetic.main.fragment_dz8_student_details.*

class Dz8StudentDetailsFragment : Fragment() {

    companion object {

        private const val ID_KEY = "id_key"

        fun getInstance(id: Long): Dz8StudentDetailsFragment {
            val fragment = Dz8StudentDetailsFragment()
            val bundle = Bundle()
            bundle.putLong(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var listener: Listener? = null
    private var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getLong(ID_KEY, -1) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val student = Dz6StudentManager.getStudentById(id)

        student?.let {
            showStudent(it)
        }

        dz8delete.setOnClickListener {
            student?.let {
                deleteStudent(it)
                listener?.onSaveOrDeleteStudentClick()
            }
        }

        dz8edit.setOnClickListener {
            listener?.onEditStudentClick(id)
        }
    }

    private fun showStudent(student: Dz6Student) {
        loadImage(student.imageUrl, dz8StudentImageView)
        dz8StudentName.text = student.name
        dz8StudentAge.text = student.age.toString()
    }

    private fun deleteStudent(student: Dz6Student) {
        Dz6StudentManager.deleteStudentById(student)
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
        fun onEditStudentClick(id: Long)
    }
}