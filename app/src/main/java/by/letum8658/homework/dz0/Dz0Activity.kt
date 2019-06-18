package by.letum8658.homework.dz0

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import by.letum8658.homework.R

class Dz0Activity : Activity() {
     private var variable = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz0)

        findViewById<View>(R.id.button)
            .setOnClickListener { changeTextAndBackground() }

        findViewById<View>(R.id.textViewOne)
            .setOnClickListener { changeTextAndBackground() }

        findViewById<View>(R.id.textViewTwo)
            .setOnClickListener { changeTextAndBackground() }
    }

    @SuppressLint("ResourceAsColor", "NewApi")
    private fun changeTextAndBackground() {
        val textViewOne = findViewById<TextView>(R.id.textViewOne)
        val textViewTwo = findViewById<TextView>(R.id.textViewTwo)
        variable = if (variable == 0) {
            textViewOne.text = getText(R.string.world)
            textViewTwo.text = getText(R.string.hello)
            textViewOne.setBackgroundColor(getColor(R.color.blue))
            textViewTwo.setBackgroundColor(getColor(R.color.red))
            1
        }else{
            textViewOne.text = getText(R.string.hello)
            textViewTwo.text = getText(R.string.world)
            textViewOne.setBackgroundColor(getColor(R.color.red))
            textViewTwo.setBackgroundColor(getColor(R.color.blue))
            0
        }
    }
}