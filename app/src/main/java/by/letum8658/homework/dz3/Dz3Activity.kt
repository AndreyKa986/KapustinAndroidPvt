package by.letum8658.homework.dz3

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import by.letum8658.homework.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Dz3Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)
        Glide
            .with(this)
            .load("http://developer.alexanderklimov.ru/android/images/android_cat.jpg")
            .apply(RequestOptions.circleCropTransform())
            .into(findViewById<ImageView>(R.id.imageView2))
    }
}