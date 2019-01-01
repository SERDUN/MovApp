package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.ui.base.BaseFragment


class MoviesGroupFragment : BaseFragment() {

    lateinit var view: MoviesGroupContract.MoviesGroupView
    lateinit var presenter: MoviesGroupContract.MoviesGroupPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_moview_group, container, false)
        view = MoviesGroupView(this, rootView)
        presenter = MoviesGroupPresenter(view)

        return rootView
    }


}
