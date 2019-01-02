package dmitriiserdun.gmail.com.movapp.ui.screen.movies_details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO
import dmitriiserdun.gmail.com.movapp.tools.Consts
import dmitriiserdun.gmail.com.movapp.ui.base.BaseFragment

class MoviesDetailsFragment : BaseFragment() {

    lateinit var view: MoviesDetailContract.MovieDetailView
    lateinit var presenter: MoviesDetailContract.MovieDetailPresenter

    private var movie: MovieDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movie = it.getSerializable(Consts.BUNDLE_MOVIE_DETAIL_LEY) as MovieDTO?
        }
        presenter = MovieDetailPresenter(movie)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var rootView = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view = MovieDetailView(this, rootView)
        presenter.initView(view)

        return rootView
    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
