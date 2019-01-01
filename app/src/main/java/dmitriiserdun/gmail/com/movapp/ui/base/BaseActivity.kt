package dmitriiserdun.gmail.com.movapp.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import dmitriiserdun.gmail.com.movapp.services.navigation.NavigationController
import dmitriiserdun.gmail.com.movapp.services.navigation.NavigationControllerImpl

abstract class BaseActivity : AppCompatActivity() {

    lateinit var navigationController: NavigationController
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        navigationController = NavigationControllerImpl(this)

        super.onCreate(savedInstanceState)
    }

    fun EditText.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 1)
    }

    open fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}