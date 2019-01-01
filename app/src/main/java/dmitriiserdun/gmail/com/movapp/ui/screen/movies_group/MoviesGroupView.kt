package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.ui.base.BaseFragment
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieAdapter
import dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters.MovieItem

class MoviesGroupView(var fragment: BaseFragment, var view: View) : MoviesGroupContract.MoviesGroupView {
    override fun setMoviesData(movies: List<MovieItem>) {
        moviesData.clear()
        moviesData.addAll(movies)
        moviesRecyclerViewAdapter.notifyDataSetChanged()
    }

    var itemDecoration: RecyclerView.ItemDecoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
                outRect.set(8, 8, 8, 8)


        }
    }


    private var moviesRecyclerView: RecyclerView = view.findViewById(R.id.rv_movies_group_movies)
    private lateinit var moviesRecyclerViewAdapter: MovieAdapter
    private var moviesData = mutableListOf<MovieItem>()

    init {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        moviesRecyclerViewAdapter = MovieAdapter(moviesData, App.instance, {})
        val layoutManager = FlexboxLayoutManager(App.instance)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        moviesRecyclerView.adapter = moviesRecyclerViewAdapter
        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.addItemDecoration(itemDecoration);
    }


}