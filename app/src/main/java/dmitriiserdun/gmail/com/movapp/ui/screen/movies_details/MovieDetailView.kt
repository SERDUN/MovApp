package dmitriiserdun.gmail.com.movapp.ui.screen.movies_details

import android.graphics.Bitmap
import android.view.View
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.ui.base.BaseFragment
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO


class MovieDetailView(var fragment: BaseFragment, var view: View) : MoviesDetailContract.MovieDetailView {
    override fun setTitleCover(title: Int) {
    }

    override fun isVisibleLoaderForCover(isVisible: Boolean) {
        if (isVisible) {
            view.findViewById<ProgressBar>(R.id.pb_movie_detail_cover).visibility = View.VISIBLE

        } else {
            view.findViewById<ProgressBar>(R.id.pb_movie_detail_cover).visibility = View.GONE

        }
    }

    override fun setMovieCover(cover: Bitmap) {
        view.findViewById<ImageView>(R.id.iv_movie_detail_cover).setImageBitmap(cover)
    }

    override fun setInformation(movie: MovieDTO) {
        view.findViewById<TextView>(R.id.tv_movie_popularity_title).setText(movie.popularity.toString())
        view.findViewById<TextView>(R.id.tv_movie_date_title).setText(movie.releaseDate.toString())
        view.findViewById<TextView>(R.id.tv_movie_vote_title).setText(movie.voteCount.toString())
        view.findViewById<TextView>(R.id.tv_movie_detail_title).setText(movie.title.toString())
        view.findViewById<TextView>(R.id.tv_movie_description_title).setText(movie.overview.toString())



    }


    init {
    }


}