package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.Observable
import io.reactivex.subjects.Subject
import org.reactivestreams.Subscriber

interface MoviesGroupContract {
    interface MoviesGroupView {
        fun setMoviesData(movies: List<MovieItem>)
        fun onBottomScrollAction(): Subject<Any>
        fun onReloadAction(): Observable<Any>
        fun isVisibleLoader(isVisible: Boolean)
        fun showError(msg: String)
        fun isShowErrorAndButtonForReload(isShow: Boolean)

    }

    interface MoviesGroupPresenter {
        fun initView(view: MoviesGroupContract.MoviesGroupView)
        fun onPause()
        fun onResume()
    }
}