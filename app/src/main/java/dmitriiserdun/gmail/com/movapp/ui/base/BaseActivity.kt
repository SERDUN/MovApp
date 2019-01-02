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

    override fun onBackPressed() {
        super.onBackPressed()
    }
}