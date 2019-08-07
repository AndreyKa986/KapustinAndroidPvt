package by.letum8658.homework.dz13

import android.app.Activity
import android.os.Bundle
import by.letum8658.homework.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dz13.*
import java.util.concurrent.TimeUnit

class Dz13Activity : Activity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz13)
    }

    override fun onStart() {
        super.onStart()

        disposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .filter { it % 2 == 0L }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                dz13TextView.text = it.toString()
            }
    }

    override fun onStop() {
        super.onStop()

        disposable?.dispose()
    }
}