package dmitriiserdun.gmail.com.movapp

import android.support.multidex.MultiDexApplication
import com.squareup.picasso.Picasso


class App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        instance = this

    }
    companion object {
        lateinit var instance: App

    }


}