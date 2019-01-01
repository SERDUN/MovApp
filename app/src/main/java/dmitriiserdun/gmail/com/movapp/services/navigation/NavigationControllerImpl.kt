package dmitriiserdun.gmail.com.movapp.services.navigation

import dmitriiserdun.gmail.com.movapp.services.factory.ScreenActivityFactory
import dmitriiserdun.gmail.com.movapp.services.factory.ScreenFragmentFactory


class NavigationControllerImpl {
    private var currentNavigationContainer: Int = 0
//    private var activeScreen = Screen.NONE
//    private var previousScreen = Screen.AUTHORIZATION_ACTIVITY
    private val fragmentFactory: ScreenFragmentFactory = ScreenFragmentFactory()
    private val activityFactory: ScreenActivityFactory = ScreenActivityFactory()
}