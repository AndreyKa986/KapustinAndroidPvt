package by.letum8658.homework.dz12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R

class Dz12Adapter(private var items: List<Student>, private val listener: ClickListener) :
    RecyclerView.Adapter<Dz12ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz12ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dz6_student, parent, false)
        val holder = Dz12ViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onStudentClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz12ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun giveStudentListBySearch(studentList: List<Student>) {
        items = studentList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onStudentClick(item: Student)
    }
}