package dmitriiserdun.gmail.com.movapp.services.factory

import android.content.Context
import android.support.v4.app.Fragment
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.MoviesGroupFragment

class ScreenFragmentFactory {
    protected var context: Context? = null

    fun getFragmentByType(type: Screen): Fragment {
        val clazz = getFragmentClassByType(type)
        return Fragment.instantiate(App.instance.applicationContext, clazz.name)
    }


    fun getFragmentClassByType(type: Screen): Class<out Fragment> {
        when (type) {
            Screen.GROUP_MOVIES -> return MoviesGroupFragment::class.java

        }
        return MoviesGroupFragment::class.java

    }
}