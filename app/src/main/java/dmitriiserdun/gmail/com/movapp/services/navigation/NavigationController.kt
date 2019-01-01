package dmitriiserdun.gmail.com.movapp.services.navigation

import android.os.Bundle

import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenAnimType
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenType

interface NavigationController {
//    fun navigateTo(screen: Screen, type: ScreenType)
//    fun navigateTo(screen: Screen, type: ScreenType, bundle: Bundle?)
//    fun navigateTo(screen: Screen, type: ScreenType, animateType: ScreenAnimType)
//    fun navigateTo(screen: Screen, type: ScreenType, bundle: Bundle?, animateType: AnimateType)
//    fun navigateTo(screen: Screen, type: ScreenType, bundle: Bundle?, animateType: AnimateType?, stackKey: String?)

    fun navigateTo(screen: Screen, type: ScreenType):NavigationController
    fun animate(animateType: ScreenAnimType):NavigationController
    fun bundle(bundle: Bundle?):NavigationController
    fun build()



    fun setNavigationContainer(int: Int)
    fun onBack()
}