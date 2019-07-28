package by.letum8658.homework.dz12

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R

class Dz12Activity : FragmentActivity(),
    Dz12ListFragment.Listener,
    Dz12DetailsFragment.Listener,
    Dz12EditFragment.Listener {

    private var isLandscape: Boolean = false
    private var container: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        container = getContainer()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8containerOne, Dz12ListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz12DetailsFragment.getInstance(id))
            .commit()
    }

    override fun onFABClick() {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz12EditFragment.getInstance(-1))
            .commit()
    }

    override fun onEditStudentClick(id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz12EditFragment.getInstance(id))
            .commit()
    }

    override fun onSaveStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz12ListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz12ListFragment())
                .commit()
        }
    }

    override fun onDeleteStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz12ListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz12ListFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerTwo)
            if (fragment is Dz12DetailsFragment || fragment is Dz12EditFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz12ListFragment())
                    .replace(R.id.dz8containerTwo, Fragment())
                    .commit()
            } else {
                super.onBackPressed()
            }
        } else {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz12ListFragment) {
                super.onBackPressed()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz12ListFragment())
                    .commit()
            }
        }
    }

    private fun getContainer() = if (isLandscape) R.id.dz8containerTwo else R.id.dz8containerOne
}