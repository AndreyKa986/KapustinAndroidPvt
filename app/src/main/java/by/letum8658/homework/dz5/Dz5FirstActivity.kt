package by.letum8658.homework.dz5

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import by.letum8658.homework.R
import kotlin.random.Random

class Dz5FirstActivity : Activity() {
    private var listNumbers = mutableListOf<Int>()
    private val random = Random
    private var numbersAmount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_first)

        val myView = findViewById<Dz5PieChart>(R.id.pieChart)

        findViewById<Button>(R.id.dz5fButton)
            .setOnClickListener {
                getArray()
                myView.listNumbers = listNumbers
            }
    }

    private fun getArray() {
        listNumbers.clear()
        numbersAmount = random.nextInt(3, 10)
        for (i in 1..numbersAmount) {
            listNumbers.add(random.nextInt(1, 20))
        }
    }
}