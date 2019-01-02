package dmitriiserdun.gmail.com.movapp.ui.screen.movies_details

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.ui.base.BaseFragment
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieAdapter
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import dmitriiserdun.gmail.com.movapp.tools.Tools
import android.support.v7.widget.GridLayoutManager
import android.view.animation.AnticipateInterpolator
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenType
import org.reactivestreams.Subscriber
import android.widget.Toast
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.json.JSONException
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.tools.Consts


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

    override fun setInformation(movie: Movie) {
        view.findViewById<TextView>(R.id.tv_movie_popularity_title).setText(movie.popularity.toString())
        view.findViewById<TextView>(R.id.tv_movie_date_title).setText(movie.releaseDate.toString())
        view.findViewById<TextView>(R.id.tv_movie_vote_title).setText(movie.voteCount.toString())
        view.findViewById<TextView>(R.id.tv_movie_detail_title).setText(movie.title.toString())
        view.findViewById<TextView>(R.id.tv_movie_description_title).setText(movie.overview.toString())



    }


    init {
    }


}