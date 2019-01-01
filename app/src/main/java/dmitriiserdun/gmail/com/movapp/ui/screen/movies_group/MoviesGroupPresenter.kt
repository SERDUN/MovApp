package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import dmitriiserdun.gmail.com.movapp.repository.Repository
import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapper
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MoviesGroupPresenter() : MoviesGroupContract.MoviesGroupPresenter {

    var nextPage: Int = 0
    var current: Int = 0
    var movies = mutableListOf<Movie>()
    lateinit var view: MoviesGroupContract.MoviesGroupView
    var activeSubscriptions: CompositeDisposable? = null

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


    override fun initView(view: MoviesGroupContract.MoviesGroupView) {
        this.view = view
        initListener()
        view.setMoviesData(movies!!.map { MovieItem(it) })


    }


    init {
        Repository.remote.getLastPopularFilms(++nextPage).subscribe(subscriberMovies)

    }

    private fun initListener() {

    }

    override fun onPause() {
        activeSubscriptions?.dispose()

    }


    override fun onResume() {
        createCompositeDisposable()
        activeSubscriptions?.add(view.onBottomScrollAction().subscribe {
            if (current == nextPage)
                Repository.remote.getLastPopularFilms(++nextPage).subscribe(subscriberMovies)

        })


    }

    private fun createCompositeDisposable() {
        if (activeSubscriptions == null)
            activeSubscriptions = CompositeDisposable()
        else {
            activeSubscriptions?.isDisposed.let {
                activeSubscriptions = CompositeDisposable()

            }
        }
    }
}