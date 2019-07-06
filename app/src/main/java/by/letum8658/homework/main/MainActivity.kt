package by.letum8658.homework.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import by.letum8658.homework.R
import by.letum8658.homework.dz0.Dz0Activity
import by.letum8658.homework.dz1.Dz1Activity
import by.letum8658.homework.dz2.Dz2ClassActivity
import by.letum8658.homework.dz2.Dz2HomeActivity
import by.letum8658.homework.dz3.Dz3Activity
import by.letum8658.homework.dz4.Dz4Activity
import by.letum8658.homework.dz5.Dz5FirstActivity
import by.letum8658.homework.dz5.Dz5SecondActivity
import by.letum8658.homework.dz6.Dz6StudentListActivity

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.dz0)
            .setOnClickListener { startDZ00() }

        findViewById<Button>(R.id.dz1)
            .setOnClickListener { startDZ01() }

        findViewById<Button>(R.id.dz2c)
            .setOnClickListener { startDZ02c() }

        findViewById<Button>(R.id.dz2h)
            .setOnClickListener { startDZ02h() }

        findViewById<Button>(R.id.dz3)
            .setOnClickListener { startDZ03() }

        findViewById<Button>(R.id.dz4)
            .setOnClickListener { startDZ04() }
      
        findViewById<Button>(R.id.dz5f)
            .setOnClickListener { startDZ05f() }

        findViewById<Button>(R.id.dz5s)
            .setOnClickListener { startDZ05s() }

        findViewById<Button>(R.id.dz6)
            .setOnClickListener { startDZ06() }
    }

    private fun startDZ00() {
        val intent = Intent(this, Dz0Activity::class.java)
        startActivity(intent)
    }

    private fun startDZ01() {
        val intent = Intent(this, Dz1Activity::class.java)
        startActivity(intent)
    }

    private fun startDZ02c() {
        val intent = Intent(this, Dz2ClassActivity::class.java)
        startActivity(intent)
    }

    private fun startDZ02h() {
        val intent = Intent(this, Dz2HomeActivity::class.java)
        startActivity(intent)
    }

    private fun startDZ03() {
        val intent = Intent(this, Dz3Activity::class.java)
        startActivity(intent)
    }

    private fun startDZ04() {
        val intent = Intent(this, Dz4Activity::class.java)
      startActivity(intent)
    }

    private fun startDZ05f() {
        val intent = Intent(this, Dz5FirstActivity::class.java)
        startActivity(intent)
    }

    private fun startDZ05s() {
        val intent = Intent(this, Dz5SecondActivity::class.java)
        startActivity(intent)
    }

    private fun startDZ06() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        startActivity(intent)
    }
}