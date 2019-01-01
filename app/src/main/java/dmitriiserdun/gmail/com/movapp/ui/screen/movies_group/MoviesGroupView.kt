package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import android.graphics.Rect
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
import dmitriiserdun.gmail.com.movapp.tools.Tools
import android.support.v7.widget.GridLayoutManager
import android.view.animation.AnticipateInterpolator
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.Screen
import dmitriiserdun.gmail.com.movapp.services.navigation.enumeration.ScreenType
import org.reactivestreams.Subscriber
import android.widget.Toast
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


class MoviesGroupView(var fragment: BaseFragment, var view: View) : MoviesGroupContract.MoviesGroupView {
    var onBottomAction = PublishSubject.create<Any>()

    override fun onBottomScrollAction(): Subject<Any> {
        return onBottomAction
    }

    override fun setMoviesData(movies: List<MovieItem>) {
        moviesData.addAll(movies)
        moviesRecyclerViewAdapter.notifyDataSetChanged()
    }

    private var itemDecoration: RecyclerView.ItemDecoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(4, 4, 4, 4)
        }
    }


    private var moviesRecyclerView: RecyclerView = view.findViewById(R.id.rv_movies_group_movies)
    private lateinit var moviesRecyclerViewAdapter: MovieAdapter
    private var moviesData = mutableListOf<MovieItem>()

    init {
        initRecyclerView()
    }

    private fun initRecyclerView() {
//        val display = fragment.activity!!.getWindowManager().getDefaultDisplay()
//        val size = Point()
//        display.getSize(size)
//        var width = 0
//
//        if (fragment.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            width = size.y
//        } else {
//            width = size.x
//        }
//        width = (width - Tools.convertDpToPixel(24f, App.instance)).toInt()/2
        moviesRecyclerViewAdapter = MovieAdapter(moviesData, App.instance) {
            fragment.getBaseActivity().navigationController.navigateTo(Screen.DETAIL_MOVIE, ScreenType.FRAGMENT).build()
        }
        val layoutManager = FlexboxLayoutManager(App.instance)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        moviesRecyclerView.adapter = moviesRecyclerViewAdapter


//        moviesRecyclerView.layoutManager = GridLayoutManager(App.instance, 2)
        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.addItemDecoration(itemDecoration);

        moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {

                    onBottomAction.onNext(Any())

                }
            }
        })
    }


}