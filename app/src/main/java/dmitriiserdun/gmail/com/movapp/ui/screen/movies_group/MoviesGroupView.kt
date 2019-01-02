package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group

import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.Snackbar
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
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import com.jakewharton.rxbinding2.view.RxView
import dmitriiserdun.gmail.com.movapp.tools.Consts
import dmitriiserdun.gmail.com.movapp.ui.base.CustomSnackBar
import io.reactivex.Observable


class MoviesGroupView(var fragment: BaseFragment, var view: View) : MoviesGroupContract.MoviesGroupView {
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesRecyclerViewAdapter: MovieAdapter
    private var moviesData = mutableListOf<MovieItem>()
    private var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    lateinit var layoutManager: FlexboxLayoutManager

    private var itemDecoration: RecyclerView.ItemDecoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(4, 4, 4, 4)
        }
    }

    init {
        initRecyclerView()
    }

    override fun onReloadAction(): Observable<Any> {
        return RxView.clicks(view.findViewById<Button>(R.id.btn_reload))
    }

    override fun isShowErrorAndButtonForReload(isShow: Boolean) {
        if (isShow) {
            view.findViewById<ViewGroup>(R.id.ll_error_container).visibility = View.VISIBLE

        } else {
            view.findViewById<ViewGroup>(R.id.ll_error_container).visibility = View.GONE
        }
    }

    override fun showError(msg: String) {
        CustomSnackBar.builder()
            .setActivity(fragment.activity!!)
            .setText(msg)
            .setDuration(Snackbar.LENGTH_LONG)
            .error()
            .show()
    }

    override fun isVisibleLoader(isVisible: Boolean) {

        if (isVisible) {
            view.findViewById<ProgressBar>(R.id.pb_movies).visibility = View.VISIBLE

        } else {
            view.findViewById<ProgressBar>(R.id.pb_movies).visibility = View.GONE
        }


    }

    var onBottomAction = PublishSubject.create<Any>()

    override fun onBottomScrollAction(): Subject<Any> {
        return onBottomAction
    }

    override fun setMoviesData(movies: List<MovieItem>) {
        Handler(Looper.getMainLooper()).postDelayed({
            moviesData.addAll(movies)
            moviesRecyclerViewAdapter.notifyDataSetChanged()
        }, 0)
    }


    private fun initRecyclerView() {
        layoutManager = FlexboxLayoutManager(dmitriiserdun.gmail.com.movapp.App.instance)
        moviesRecyclerView = view.findViewById(R.id.rv_movies_group_movies)
        moviesRecyclerViewAdapter = MovieAdapter(moviesData, App.instance) {
            var bundle = Bundle()
            bundle.putSerializable(Consts.BUNDLE_MOVIE_DETAIL_LEY, it.dto)
            fragment.getBaseActivity().navigationController.navigateTo(Screen.DETAIL_MOVIE, ScreenType.FRAGMENT)
                .bundle(bundle).build()
        }
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        moviesRecyclerView.adapter = moviesRecyclerViewAdapter


        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.addItemDecoration(itemDecoration);


        moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
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