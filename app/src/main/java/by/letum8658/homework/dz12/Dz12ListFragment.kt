package by.letum8658.homework.dz12

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import by.letum8658.homework.dz8.AppPrefManager
import kotlinx.android.synthetic.main.fragment_dz8_student_list.*

class Dz12ListFragment : Fragment(), Dz12ListView, Dz12Adapter.ClickListener {

    private val presenter = Dz12ListPresenter()
    private var listener: Listener? = null
    private lateinit var adapter: Dz12Adapter
    private var searchString: String = ""
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz12_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)

        progressBar = view.findViewById(R.id.dz12_progress_circular)

        val recycleView = view.findViewById<RecyclerView>(R.id.dz8recyclerView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)

        adapter = Dz12Adapter(presenter.getDatabase(), this)
        recycleView.adapter = adapter

        dz8searchEditText.setText(presenter.getTextForSearch())
        dz8searchEditText.addTextChangedListener(object : TextWatcher {

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

    override fun onStudentClick(item: Student) {
        listener?.onStudentClick(item.id)
    }

    override fun onResume() {
        super.onResume()
        val text = presenter.getTextForSearch()
        if (text != searchString) {
            updateList()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.saveTextForSearch(dz8searchEditText.text.toString())
        searchString = dz8searchEditText.text.toString()
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
        presenter.dispose()
    }

    fun updateList() {
        val text = dz8searchEditText.text.toString()
        adapter.giveStudentListBySearch(presenter.getSearchList(text))
    }

    interface Listener {
        fun onStudentClick(id: String)
        fun onFABClick()
    }

    override fun getPrefsManager(): AppPrefManager = AppPrefManager(context!!)

    override fun requirePrefsManager(): AppPrefManager = AppPrefManager(requireContext())

    override fun updateDatabase() {
        updateList()
    }

    override fun progressBarOn() {
        progressBar.visibility = View.VISIBLE
    }

    override fun progressBarOff() {
        progressBar.visibility = View.INVISIBLE
    }
}