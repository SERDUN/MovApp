package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem

interface MoviesGroupContract {
    interface MoviesGroupView{
        fun setMoviesData(movies:List<MovieItem>)
    }
    interface MoviesGroupPresenter{}
}