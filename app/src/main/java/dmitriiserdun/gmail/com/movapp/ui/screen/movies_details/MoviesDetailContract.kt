package dmitriiserdun.gmail.com.movapp.ui.screen.movies_details

import android.graphics.Bitmap
import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO

interface MoviesDetailContract {
    interface MovieDetailView{
        fun setMovieCover(cover:Bitmap)
        fun setTitleCover(title:Int)
        fun isVisibleLoaderForCover(isVisible:Boolean)
        fun setInformation(movie: MovieDTO)

    }
    interface MovieDetailPresenter{
       fun initView( view: MovieDetailView)
        fun onPause()
        fun onResume()
    }
}