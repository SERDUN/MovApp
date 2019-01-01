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
import org.json.JSONException
import android.support.v7.widget.LinearLayoutManager


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
    private var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    val layoutManager = FlexboxLayoutManager(App.instance)

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
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        moviesRecyclerView.adapter = moviesRecyclerViewAdapter


        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.addItemDecoration(itemDecoration);


        moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                {
                    visibleItemCount = layoutManager.getChildCount()
                    totalItemCount = layoutManager.getItemCount()
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            onBottomAction.onNext(Any())
                        }
                    }
                }
            }
        })


}


}