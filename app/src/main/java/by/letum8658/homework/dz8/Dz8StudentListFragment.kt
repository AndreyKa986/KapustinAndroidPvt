package by.letum8658.homework.dz8

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import by.letum8658.homework.dz6.Dz6Student
import by.letum8658.homework.dz6.Dz6StudentListAdapter
import by.letum8658.homework.dz6.Dz6StudentManager
import kotlinx.android.synthetic.main.fragment_dz8_student_list.*

class Dz8StudentListFragment : Fragment(), Dz6StudentListAdapter.ClickListener {

    private var listener: Listener? = null
    private lateinit var prefsManager: AppPrefManager
    private lateinit var search: EditText
    private lateinit var adapter: Dz6StudentListAdapter
    private var searchString: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycleView = view.findViewById<RecyclerView>(R.id.dz8recyclerView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)

        val database = Dz6StudentManager.getStudentList()
        adapter = Dz6StudentListAdapter(database, this)
        recycleView.adapter = adapter

        prefsManager = AppPrefManager(context!!)
        search = dz8searchEditText
        search.setText(prefsManager.getSearchText())
        search.addTextChangedListener(object : TextWatcher {

            private val handler = Handler(Looper.getMainLooper())
            private lateinit var workRunnable: Runnable

            override fun afterTextChanged(string: Editable?) {
                workRunnable = Runnable { updateList() }
                handler.removeCallbacks(workRunnable)
                handler.postDelayed(workRunnable, 300L)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        dz8FAB.setOnClickListener {
            listener?.onFABClick()
        }
    }

    private fun searchByStudents(text: String) {
        adapter.giveStudentListBySearch(Dz6StudentManager.searchList(text))
    }

    override fun onStudentClick(item: Dz6Student) {
        listener?.onStudentClick(item.id)
    }

    override fun onResume() {
        super.onResume()
        val text = prefsManager.getSearchText()
        if (text != searchString) {
            updateList()
        }
    }

    override fun onStop() {
        super.onStop()
        prefsManager.saveSearchText(search.text.toString())
        searchString = search.text.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prefsManager = AppPrefManager(requireContext())
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

    fun updateList() {
        searchByStudents(search.text.toString())
    }

    interface Listener {
        fun onStudentClick(id: Long)
        fun onFABClick()
    }
}