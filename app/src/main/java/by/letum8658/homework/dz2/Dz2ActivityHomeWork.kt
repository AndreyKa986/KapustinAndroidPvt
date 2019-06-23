package by.letum8658.homework.dz2

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import by.letum8658.homework.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class Dz2ActivityHomeWork : Activity() {
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var link: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2_homework)
        progressBar = findViewById(R.id.progress_circular)
        progressBar.visibility = View.INVISIBLE
        image = findViewById(R.id.pictureFromLink)
        link = findViewById(R.id.linkForPicture)
        findViewById<Button>(R.id.showPictureButton)
            .setOnClickListener {
                loadAndShowPicture()
            }
    }

    private fun loadAndShowPicture() {
        progressBar.visibility = View.VISIBLE
        Glide
            .with(this)
            .load(link.text.toString())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this@Dz2ActivityHomeWork, "Sorry, load was failed\n" +
                                "You can try with test link", Toast.LENGTH_SHORT
                    )
                        .show()
                    link.setText("http://developer.alexanderklimov.ru/android/images/android_cat.jpg")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.INVISIBLE
                    link.text.clear()
                    return false
                }
            })
            .apply(RequestOptions.circleCropTransform())
            .into(image)
    }
}