package by.letum8658.homework.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import kotlinx.android.synthetic.main.activity_dz6_student_list.*

class Dz6StudentListActivity : Activity(), Dz6StudentListAdapter.ClickListener {

    private lateinit var adapter: Dz6StudentListAdapter
    private var searchString = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_list)

        val recycleView = findViewById<RecyclerView>(R.id.dz6recyclerView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)

        val database = Dz6StudentManager.getStudentList()
        adapter = Dz6StudentListAdapter(database, this)
        recycleView.adapter = adapter

        val search = dz6searchEditText
        search.text.clear()
        search.addTextChangedListener(object : TextWatcher {

            private val handler = Handler(Looper.getMainLooper())
            private lateinit var workRunnable: Runnable

            override fun afterTextChanged(string: Editable?) {

                workRunnable = Runnable {
                    searchString = string.toString()
                    searchByStudents(searchString)
                }
                handler.removeCallbacks(workRunnable)
                handler.postDelayed(workRunnable, 300L)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        dz6FAB.setOnClickListener {
            val intent = Intent(this, Dz6StudentEditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun searchByStudents(text: String) {
        adapter.giveStudentListBySearch(Dz6StudentManager.searchList(text))
    }

    override fun onStudentClick(item: Dz6Student) {
        startActivity(Dz6StudentDetailsActivity.getIntent(this, item.id))
    }

    override fun onResume() {
        super.onResume()
        searchByStudents(searchString)
    }
}