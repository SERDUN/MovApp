package dmitriiserdun.gmail.com.movapp

import android.support.multidex.MultiDexApplication


class App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()

        instance = this

    }
    companion object {
        lateinit var instance: App

    }


}