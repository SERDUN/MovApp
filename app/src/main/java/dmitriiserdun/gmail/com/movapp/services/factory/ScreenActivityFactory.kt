package dmitriiserdun.gmail.com.movapp.services.factory

import android.app.Activity
import android.content.Intent
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.ui.screen.MainActivity

class ScreenActivityFactory {
    fun getActivityByType(type: Screen): Intent {
        val clazz = getActivityClassByType(type)
        return Intent(App.instance.applicationContext, clazz)
    }

    fun getActivityClassByType(type: Screen): Class<out Activity> {

        return MainActivity::class.java
    }
}