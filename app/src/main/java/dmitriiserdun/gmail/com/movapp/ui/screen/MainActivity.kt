package dmitriiserdun.gmail.com.movapp.ui.screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenType
import dmitriiserdun.gmail.com.movapp.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController.setNavigationContainer(R.id.fl_main_container)
        navigationController.navigateTo(Screen.GROUP_MOVIES, ScreenType.FRAGMENT).build()
    }
}
