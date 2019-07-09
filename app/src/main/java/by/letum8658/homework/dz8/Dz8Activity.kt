package by.letum8658.homework.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import by.letum8658.homework.R

private const val BACK_STACK_NAME = "back_stack_name"

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
            .addToBackStack(BACK_STACK_NAME)
            .commit()
    }

    override fun onFABClick() {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz8StudentEditFragment.getInstance(-1))
            .addToBackStack(BACK_STACK_NAME)
            .commit()
    }

    override fun onEditStudentClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .replace(container, Dz8StudentEditFragment.getInstance(id))
            .addToBackStack(BACK_STACK_NAME)
            .commit()
    }

    override fun onSaveOrDeleteStudentClick() {
        onBackPressed()
        val fragment = supportFragmentManager.findFragmentById(R.id.dz8containerOne)
        if (fragment is Dz8StudentListFragment) {
            fragment.updateList()
        }
    }

    override fun onBackPressed() {
        if (!supportFragmentManager.popBackStackImmediate(BACK_STACK_NAME, POP_BACK_STACK_INCLUSIVE)) {
            super.onBackPressed()
        }
    }

    private fun getContainer() = if (isLandscape) R.id.dz8containerTwo else R.id.dz8containerOne
}