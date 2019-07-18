package by.letum8658.homework.dz9

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import by.letum8658.homework.dz9.entity.Poi

class Dz9ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val fleetTextView = itemView.findViewById<TextView>(R.id.dz9itemFleetType)
    private val idTextView = itemView.findViewById<TextView>(R.id.dz9itemId)

    fun bind(item: Poi) {
        fleetTextView.text = item.fleetType.toString()
        idTextView.text = item.id
    }
}