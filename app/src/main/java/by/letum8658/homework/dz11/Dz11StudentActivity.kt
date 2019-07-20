package by.letum8658.homework.dz11

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R

class Dz11StudentActivity : FragmentActivity(),
    Dz11ListFragment.Listener,
    Dz11DetailsFragment.Listener,
    Dz11EditFragment.Listener {

    private var isLandscape: Boolean = false
    private var container: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        container = getContainer()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8containerOne, Dz11ListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz11DetailsFragment.getInstance(id))
            .commit()
    }

    override fun onFABClick() {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz11EditFragment.getInstance(-1))
            .commit()
    }

    override fun onEditStudentClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz11EditFragment.getInstance(id))
            .commit()
    }

    override fun onSaveStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz11ListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz11ListFragment())
                .commit()
        }
    }

    override fun onDeleteStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz11ListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz11ListFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerTwo)
            if (fragment is Dz11DetailsFragment || fragment is Dz11EditFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz11ListFragment())
                    .replace(R.id.dz8containerTwo, Fragment())
                    .commit()
            } else {
                super.onBackPressed()
            }
        } else {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz11ListFragment) {
                super.onBackPressed()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz11ListFragment())
                    .commit()
            }
        }
    }

    private fun getContainer() = if (isLandscape) R.id.dz8containerTwo else R.id.dz8containerOne
}