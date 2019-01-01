package dmitriiserdun.gmail.com.movapp.ui.screen.movies_group.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitriiserdun.gmail.com.movapp.R
import kotlinx.android.synthetic.main.item_recycler_view_movie.view.*
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.BuildConfig
import java.lang.Exception
import android.widget.Toast
import dmitriiserdun.gmail.com.movapp.ui.screen.MainActivity
import android.support.v7.graphics.Palette
import android.R.attr.bitmap




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


            Picasso.get().load(BuildConfig.API_IMAGE_URL + item.dto.backdropPath)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        Palette.from(bitmap!!)
                            .generate(Palette.PaletteAsyncListener { palette ->
                                val textSwatch = palette!!.vibrantSwatch
                                if (textSwatch == null) {
                                    return@PaletteAsyncListener
                                }
                                tv_item_name_rv.setTextColor(textSwatch.titleTextColor)
                                tv_item_rating_rv.setTextColor(textSwatch.bodyTextColor)
                                iv_info_container_rv.setBackgroundColor(textSwatch.rgb)
                            })
                        iv_item_rv.setImageBitmap(bitmap)
                    }
                });

            tv_item_name_rv.text = item.dto.originalTitle
            tv_item_rating_rv.text = item.dto.popularity.toString() + "/10 " + "(" + item.dto.voteCount + " votes)"
        }
    }

}
