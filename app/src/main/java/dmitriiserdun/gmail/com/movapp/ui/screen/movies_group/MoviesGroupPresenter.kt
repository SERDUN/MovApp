package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import android.util.Log
import dmitriiserdun.gmail.com.movapp.repository.Repository
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapper
import dmitriiserdun.gmail.com.movapp.tools.onMainThread
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MoviesGroupPresenter(var view: MoviesGroupContract.MoviesGroupView) : MoviesGroupContract.MoviesGroupPresenter {
    init {
        var t = Repository.remote.getLastPopularFilms().subscribe(object : Observer<PaginationWrapper<Movie>> {
            override fun onComplete() = Unit
            override fun onSubscribe(d: Disposable) = Unit

            override fun onNext(t: PaginationWrapper<Movie>) {
                Log.d("test", "test")
            }

            override fun onError(e: Throwable) {
                Log.d("test", "test")

            }
        })
    }
}