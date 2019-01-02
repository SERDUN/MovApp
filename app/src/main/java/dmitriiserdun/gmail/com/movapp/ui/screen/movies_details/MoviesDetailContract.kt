package dmitriiserdun.gmail.com.movapp.ui.screen.movies_details

import android.graphics.Bitmap
import android.graphics.Color
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.subjects.Subject

interface MoviesDetailContract {
    interface MovieDetailView{
        fun setMovieCover(cover:Bitmap)
        fun setTitleCover(title:Int)
        fun isVisibleLoaderForCover(isVisible:Boolean)
        fun setInformation(movie: Movie)

    }
    interface MovieDetailPresenter{
       fun initView( view: MovieDetailView)
        fun onPause()
        fun onResume()
    }
}