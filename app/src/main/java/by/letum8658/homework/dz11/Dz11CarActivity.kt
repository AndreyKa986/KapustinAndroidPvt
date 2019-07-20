package by.letum8658.homework.dz11

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R

class Dz11CarActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.dz9BottomSheetContainer, Dz11CarFragment())
        }
    }
}