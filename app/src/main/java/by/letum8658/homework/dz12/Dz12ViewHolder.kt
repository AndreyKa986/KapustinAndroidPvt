package by.letum8658.homework.dz12

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import by.letum8658.homework.utils.loadImage

class Dz12ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = itemView.findViewById<ImageView>(R.id.dz6ItemImageView)
    private val textView = itemView.findViewById<TextView>(R.id.dz6ItemTextView)

    fun bind(item: Student) {
        loadImage(item.imageUrl, imageView)
        textView.text = item.name
    }
}