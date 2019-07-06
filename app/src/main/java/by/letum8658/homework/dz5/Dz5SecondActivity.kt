package by.letum8658.homework.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import by.letum8658.homework.R

class Dz5SecondActivity : Activity() {
    private lateinit var owlAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_second)

        owlAnimation = findViewById<ImageView>(R.id.imageview_animation_list).background as AnimationDrawable
    }

    override fun onStart() {
        super.onStart()
        owlAnimation.start()
    }

    override fun onStop() {
        super.onStop()
        owlAnimation.stop()
    }
}