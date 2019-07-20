package by.letum8658.homework.dz11

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
import by.letum8658.homework.dz8.AppPrefManager
import kotlinx.android.synthetic.main.fragment_dz8_student_list.*

class Dz11ListFragment : Fragment(), Dz11ListView, Dz6StudentListAdapter.ClickListener {

    private val presenter = Dz11ListPresenter()
    private var listener: Listener? = null
    private lateinit var search: EditText
    private lateinit var adapter: Dz6StudentListAdapter
    private var searchString: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)

        val recycleView = view.findViewById<RecyclerView>(R.id.dz8recyclerView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)

        adapter = Dz6StudentListAdapter(presenter.getDatabase(), this)
        recycleView.adapter = adapter

        search = dz8searchEditText
        search.setText(presenter.getTextForSearh())
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

    override fun onStudentClick(item: Dz6Student) {
        listener?.onStudentClick(item.id)
    }

    override fun onResume() {
        super.onResume()
        val text = presenter.getTextForSearh()
        if (text != searchString) {
            updateList()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.saveTextForSearch(search.text.toString())
        searchString = search.text.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.requirePrefsManager()
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
        val text = search.text.toString()
        adapter.giveStudentListBySearch(presenter.getSearchList(text))
    }

    interface Listener {
        fun onStudentClick(id: Long)
        fun onFABClick()
    }

    override fun getPrefsManager(): AppPrefManager = AppPrefManager(context!!)

    override fun requirePrefsManager(): AppPrefManager = AppPrefManager(requireContext())
}