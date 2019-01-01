package dmitriiserdun.gmail.com.movapp

import android.support.multidex.MultiDexApplication
import dmitriiserdun.gmail.com.movapp.repository.remote.RestManagerImpl


class App : MultiDexApplication() {

    val api: App by lazy { RestManagerImpl().api }

    override fun onCreate() {
        super.onCreate()
        instance = this


    }

    companion object {
        lateinit var instance: App
            private set


    }
}