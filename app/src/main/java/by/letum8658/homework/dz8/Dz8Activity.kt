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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container, Dz8StudentDetailsFragment.getInstance(id))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onFABClick() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container, Dz8StudentEditFragment.getInstance(-1))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onEditStudentClick(id: Long) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container, Dz8StudentEditFragment.getInstance(id))
        transaction.commit()
    }

    override fun onSaveOrDeleteStudentClick() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dz8containerOne, Dz8StudentListFragment())
        if (isLandscape) {
            transaction.replace(R.id.dz8containerTwo, Fragment())
        }
        supportFragmentManager.popBackStack()
        transaction.commit()
    }

    private fun getContainer(): Int {
        return when (isLandscape) {
            true -> R.id.dz8containerTwo
            false -> R.id.dz8containerOne
        }
    }
}