package by.letum8658.homework.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R

class Dz8Activity : FragmentActivity(),
    Dz8StudentListFragment.Listener,
    Dz8StudentDetailsFragment.Listener,
    Dz8StudentEditFragment.Listener {

    private var isLandscape: Boolean = false
    private var container: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        container = getContainer()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8containerOne, Dz8StudentListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz8StudentDetailsFragment.getInstance(id))
            .commit()
    }

    override fun onFABClick() {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz8StudentEditFragment.getInstance(-1))
            .commit()
    }

    override fun onEditStudentClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz8StudentEditFragment.getInstance(id))
            .commit()
    }

    override fun onSaveStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz8StudentListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz8StudentListFragment())
                .commit()
        }
    }

    override fun onDeleteStudentClick() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz8StudentListFragment) {
                fragment.updateList()
            }
            supportFragmentManager.beginTransaction()
                .replace(container, Fragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container, Dz8StudentListFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (isLandscape) {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerTwo)
            if (fragment is Dz8StudentDetailsFragment || fragment is Dz8StudentEditFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz8StudentListFragment())
                    .replace(R.id.dz8containerTwo, Fragment())
                    .commit()
            } else {
                super.onBackPressed()
            }
        } else {
            val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
            if (fragment is Dz8StudentListFragment) {
                super.onBackPressed()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dz8containerOne, Dz8StudentListFragment())
                    .commit()
            }
        }
    }

    private fun getContainer() = if (isLandscape) R.id.dz8containerTwo else R.id.dz8containerOne
}