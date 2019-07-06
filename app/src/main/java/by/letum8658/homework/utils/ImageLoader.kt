package by.letum8658.homework.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

fun loadImage(url: String, imageView: ImageView) {
    Glide
        .with(imageView.context)
        .load(url)
        .into(imageView)
}

fun loadImage(url: String, listener: RequestListener<Drawable>, imageView: ImageView) {
    Glide
        .with(imageView.context)
        .load(url)
        .listener(listener)
        .into(imageView)
}

fun loadCircleImage(url: String, imageView: ImageView) {
    Glide
        .with(imageView.context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(imageView)
}

fun loadCircleImage(url: String, listener: RequestListener<Drawable>, imageView: ImageView) {
    Glide
        .with(imageView.context)
        .load(url)
        .listener(listener)
        .apply(RequestOptions.circleCropTransform())
        .into(imageView)
}