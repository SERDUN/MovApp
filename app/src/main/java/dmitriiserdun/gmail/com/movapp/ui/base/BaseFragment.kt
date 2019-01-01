package dmitriiserdun.gmail.com.movapp.ui.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dmitriiserdun.gmail.com.movapp.R
import java.lang.Exception


open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
    }

    fun getBaseActivity(): BaseActivity {
        if (!(activity is BaseActivity)) Exception("this fragment must be in the base activity")
        return activity as BaseActivity
    }


}
