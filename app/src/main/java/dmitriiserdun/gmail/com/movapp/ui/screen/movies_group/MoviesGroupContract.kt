package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem
import io.reactivex.subjects.Subject
import org.reactivestreams.Subscriber

interface MoviesGroupContract {
    interface MoviesGroupView{
        fun setMoviesData(movies:List<MovieItem>)
        fun onBottomScrollAction():Subject<Any>
    }
    interface MoviesGroupPresenter{}
}