package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import dmitriiserdun.gmail.com.movapp.repository.Repository
import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapperDTO
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MoviesGroupPresenter() : MoviesGroupContract.MoviesGroupPresenter {
    var nextPage: Int = 0
    var current: Int = 0
    var movies = mutableListOf<MovieDTO>()
    lateinit var view: MoviesGroupContract.MoviesGroupView
    var activeSubscriptions: CompositeDisposable? = null
    var requestSubscriptions: Disposable? = null

    var requestSubscriptionsMovies = object : Observer<PaginationWrapperDTO<MovieDTO>> {
        override fun onComplete()=Unit
        override fun onSubscribe(d: Disposable) {
            if (requestSubscriptions != null) {
                if (!requestSubscriptions!!.isDisposed) {
                    requestSubscriptions!!.dispose()
                }
            }
            requestSubscriptions = d
        }

        override fun onNext(t: PaginationWrapperDTO<MovieDTO>) {
            view.isShowErrorAndButtonForReload(false)
            view.isVisibleLoader(false)

            if (current!! < t.page!!) {
                nextPage = t.page!!
                movies.addAll(t.results!!)

                view.setMoviesData(t.results!!.map { MovieItem(it) })
                current = t.page!!

            }

        }

        override fun onError(e: Throwable) {
            if (movies.isEmpty()) {
                view.isShowErrorAndButtonForReload(true)
            } else {
                view.isShowErrorAndButtonForReload(false)
            }
            nextPage--;
            view.showError(e.localizedMessage)
            view.isVisibleLoader(false)
        }
    }

    init {
        Repository.remote.getLastPopularFilms(++nextPage).subscribe(requestSubscriptionsMovies)

    }


    override fun initView(view: MoviesGroupContract.MoviesGroupView) {
        view.isShowErrorAndButtonForReload(false)
        this.view = view
        initListener()
        if (movies.isEmpty()) {
            view.isVisibleLoader(true)
            Repository.remote.getLastPopularFilms(++nextPage).subscribe(requestSubscriptionsMovies)
        } else {
            view.isVisibleLoader(false)
                view.setMoviesData(movies!!.map { MovieItem(it) })
        }


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
                Repository.remote.getLastPopularFilms(++nextPage).subscribe(requestSubscriptionsMovies)

        })
        activeSubscriptions?.add(view.onReloadAction().subscribe {
            Repository.remote.getLastPopularFilms(++nextPage).subscribe(requestSubscriptionsMovies)

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