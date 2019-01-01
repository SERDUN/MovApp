package dmitriiserdun.gmail.com.movapp.services.factory

import android.content.Context
import android.support.v4.app.Fragment
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen

class ScreenFragmentFactory {
    protected var context: Context? = null

    fun getFragmentByType(type: Screen): Fragment {
        val clazz = getFragmentClassByType(type)
        return Fragment.instantiate(App.instance.applicationContext, clazz.name)
    }


    fun getFragmentClassByType(type: Screen): Class<out Fragment> {
        when (type) {
            Screen. -> return ::class.java

        }
        return ::class.java

    }
}