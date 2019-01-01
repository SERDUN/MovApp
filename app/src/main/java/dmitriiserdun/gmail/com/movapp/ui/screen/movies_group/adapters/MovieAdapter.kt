package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dmitriiserdun.gmail.com.movapp.R
import kotlinx.android.synthetic.main.item_recycler_view_movie.view.*
import com.bumptech.glide.request.RequestOptions
import dmitriiserdun.gmail.com.movapp.BuildConfig


class MovieAdapter(val items: List<MovieItem>, val context: Context, val listener: (MovieItem) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener, items)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MovieItem, listener: (MovieItem) -> Unit, items: List<MovieItem>) = with(itemView) {

            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)

            Glide.with(this).load(BuildConfig.API_IMAGE_URL+item.dto.backdropPath).apply(options).into(iv_item_rv)

        }
    }

}
