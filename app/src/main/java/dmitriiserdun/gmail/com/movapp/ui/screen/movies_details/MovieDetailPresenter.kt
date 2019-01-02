package dmitriiserdun.gmail.com.movapp.ui.screen.movies_details

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.BuildConfig
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import kotlinx.android.synthetic.main.item_recycler_view_movie.view.*
import java.lang.Exception

class MovieDetailPresenter(var movie: Movie?) : MoviesDetailContract.MovieDetailPresenter {
    lateinit var view: MoviesDetailContract.MovieDetailView

    override fun initView(view: MoviesDetailContract.MovieDetailView) {
        this.view = view
        loadMovieCover()
    }

    private fun loadMovieCover() {
        Picasso.get().load(BuildConfig.API_IMAGE_URL + movie!!.backdropPath)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    view.isVisibleLoaderForCover(true)


                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    view.isVisibleLoaderForCover(false)


                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    Palette.from(bitmap!!)
                        .generate(Palette.PaletteAsyncListener { palette ->
                            val textSwatch = palette!!.vibrantSwatch ?: return@PaletteAsyncListener
                            view.setTitleCover(textSwatch.titleTextColor)

                        })
                    view.setMovieCover(bitmap)
                    view.isVisibleLoaderForCover(false)

                }
            });
        view.setInformation(movie!!)

    }

    override fun onPause() {
    }

    override fun onResume() {
    }

}