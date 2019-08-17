package by.letum8658.homework.dz15

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R

class Dz15Activity : FragmentActivity(),
    Dz15ListFragment.Listener,
    Dz15DetailsFragment.Listener,
    Dz15EditFragment.Listener {

    private var isLandscape: Boolean = false
    private var container: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        container = getContainer()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8containerOne, Dz15ListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: String) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz15DetailsFragment.getInstance(id))
            .commit()
    }

    override fun onFABClick() {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz15EditFragment.getInstance(" "))
            .commit()
    }

    override fun onEditStudentClick(id: String) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz15EditFragment.getInstance(id))
            .commit()
    }

    override fun onSaveStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz15ListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz15ListFragment())
                .commit()
        }
    }

    override fun onDeleteStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz15ListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz15ListFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerTwo)
            if (fragment is Dz15DetailsFragment || fragment is Dz15EditFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz15ListFragment())
                    .replace(R.id.dz8containerTwo, Fragment())
                    .commit()
            } else {
                super.onBackPressed()
            }
        } else {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz15ListFragment) {
                super.onBackPressed()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz15ListFragment())
                    .commit()
            }
        }
    }

    private fun getContainer() = if (isLandscape) R.id.dz8containerTwo else R.id.dz8containerOne
}