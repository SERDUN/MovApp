package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import android.util.Log
import dmitriiserdun.gmail.com.movapp.repository.Repository
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapper
import dmitriiserdun.gmail.com.movapp.tools.onMainThread
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MoviesGroupPresenter(var view: MoviesGroupContract.MoviesGroupView) : MoviesGroupContract.MoviesGroupPresenter {
    init {
        var items = mutableListOf<MovieItem>()


        Repository.remote.getLastPopularFilms().subscribe(object : Observer<PaginationWrapper<Movie>> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: PaginationWrapper<Movie>) {
                view.setMoviesData(t.results!!.map { MovieItem(it) })
            }

            override fun onError(e: Throwable) {
            }
        })
    }
}