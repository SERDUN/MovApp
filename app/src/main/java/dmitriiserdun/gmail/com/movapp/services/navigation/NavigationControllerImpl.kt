package dmitriiserdun.gmail.com.movapp.services.navigation

import android.os.Bundle
import android.util.Log
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.services.factory.ScreenActivityFactory
import dmitriiserdun.gmail.com.movapp.services.factory.ScreenFragmentFactory
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenAnimType
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenType
import dmitriiserdun.gmail.com.movapp.ui.base.BaseActivity


class NavigationControllerImpl(private val activity: BaseActivity) : NavigationController {
    private var currentNavigationContainer: Int = 0

    private val fragmentFactory: ScreenFragmentFactory = ScreenFragmentFactory()
    private val activityFactory: ScreenActivityFactory = ScreenActivityFactory()
    private var prepareScreen: Screen = Screen.NONE
    private var prepareType: ScreenType = ScreenType.ACTIVITY
    private var prepareAnimate: ScreenAnimType? = null
    private var prepareBundle: Bundle? = null


    override fun navigateTo(screen: Screen, type: ScreenType): NavigationController {
        this.prepareScreen = screen
        this.prepareType = type
        return this
    }

    override fun animate(animateType: ScreenAnimType): NavigationController {
        this.prepareAnimate = animateType
        return this
    }

    override fun bundle(bundle: Bundle?): NavigationController {
        this.prepareBundle = bundle
        return this
    }

    override fun build() {
        when (prepareType) {
            ScreenType.ACTIVITY -> navigateToActivity(prepareScreen, null)
            ScreenType.FRAGMENT -> navigateToFragment(prepareScreen, prepareBundle, prepareAnimate)
        }
        prepareType = ScreenType.ACTIVITY
        prepareScreen = Screen.NONE
        prepareBundle = null
        prepareAnimate = null
    }

    override fun setNavigationContainer(idContainer: Int) {
        this.currentNavigationContainer = idContainer
    }


    private fun navigateToActivity(screen: Screen, bundle: Bundle?) {


    }

    private fun navigateToFragment(screen: Screen, bundle: Bundle?, animateType: ScreenAnimType?) {
        when (screen) {
            Screen.GROUP_MOVIES -> navigateGroupMoviesFragment(screen, bundle, animateType)
        }

    }

    private fun navigateGroupMoviesFragment(screen: Screen, bundle: Bundle?, animateType: ScreenAnimType?) {
        switchFragmentScreen(
            screen, bundle, false, true, currentNavigationContainer
            , getAnimationByType(animateType)
        )


    }


    private fun switchFragmentScreen(
        type: Screen, bundle: Bundle?, animate: Boolean, addToBackStack: Boolean
        , idContainer: Int, animateType: IntArray? = null
    ) {
        if (isSameFragmentAlreadyPlaced(type))
            return

        val fragmentManager = activity.supportFragmentManager
        val tran = fragmentManager.beginTransaction()
        if (animate) {
            tran.setCustomAnimations(R.anim.popup_in, R.anim.popup_out)
        }

        val fragment = fragmentFactory.getFragmentByType(type)
        if (bundle != null && !bundle.isEmpty) {
            fragment.arguments = bundle
        }
        if (addToBackStack) {
            if (animate) {
                if (animateType == null)
                    tran.setCustomAnimations(
                        R.anim.right_to_left_in,
                        R.anim.right_to_left_out,
                        R.anim.left_to_right_in,
                        R.anim.left_to_right_out
                    ) else {
                    if (animateType.size == 2) {
                        tran.setCustomAnimations(
                            animateType[0],
                            animateType[1]
                        )
                    } else
                        tran.setCustomAnimations(
                            animateType[0],
                            animateType[1],
                            animateType[2],
                            animateType[3]
                        )
                }

            }
            tran.replace(idContainer, fragment, fragment.javaClass.simpleName)
            var currentStackKey = fragment.javaClass.simpleName
            tran.addToBackStack(currentStackKey)
        } else {
            if (animate) {
                tran.setCustomAnimations(R.anim.popup_in, R.anim.popup_out)
            }
            tran.replace(idContainer, fragment)
        }
        try {
            tran.commitAllowingStateLoss()
        } catch (e: Exception) {
            Log.e(TAG, "Activity destroyed")
        }

    }


    private fun isSameFragmentAlreadyPlaced(type: Screen): Boolean {
        val existing = activity.supportFragmentManager.findFragmentById(currentNavigationContainer)
        if (existing != null) {
            val requested = fragmentFactory.getFragmentClassByType(type)
            if (existing.javaClass == requested) {
                return true
            }
        }
        return false
    }

    companion object {
        private val TAG = NavigationControllerImpl::class.java.simpleName
    }

    fun getAnimationByType(type: ScreenAnimType?): IntArray? {
        var intArray: IntArray? = null
        type?.let {
            when (type) {
                ScreenAnimType.FADE_TYPE -> {
                    intArray = intArrayOf(
                        R.anim.fscv_fade_in,
                        R.anim.fscv_fade_out,
                        R.anim.fscv_fade_in,
                        R.anim.fscv_fade_out
                    )
                }
            }
        }



        return intArray
    }


}