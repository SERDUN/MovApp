package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import dmitriiserdun.gmail.com.movapp.repository.Repository
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapper
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MoviesGroupPresenter(var view: MoviesGroupContract.MoviesGroupView) : MoviesGroupContract.MoviesGroupPresenter {
    var nextPage: Int = 0
    var current: Int = 0
    var movies = mutableListOf<Movie>()


    var subscriberMovies = object : Observer<PaginationWrapper<Movie>> {
        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: PaginationWrapper<Movie>) {

            if (current!! < t.page!!) {
                nextPage = t.page!!
                movies.addAll(t.results!!)
                view.setMoviesData(t.results!!.map { MovieItem(it) })
                current = t.page!!


            }

        }

        override fun onError(e: Throwable) {
            nextPage--;
        }
    }

    init {
        Repository.remote.getLastPopularFilms(++nextPage).subscribe(subscriberMovies)


        view.onBottomScrollAction().subscribe {
            if(current==nextPage)
            Repository.remote.getLastPopularFilms(++nextPage).subscribe(subscriberMovies)

        }
    }
}