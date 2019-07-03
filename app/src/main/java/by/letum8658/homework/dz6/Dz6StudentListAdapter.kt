package by.letum8658.homework.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R

class Dz6StudentListAdapter(private var items: List<Dz6Student>, private val listener: ClickListener) :
    RecyclerView.Adapter<Dz6StudentListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6StudentListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dz6_student, parent, false)
        val holder = Dz6StudentListViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onStudentClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz6StudentListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun searchAdapter(studentList: List<Dz6Student>) {
        items = studentList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onStudentClick(item: Dz6Student)
    }
}