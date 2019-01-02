package dmitriiserdun.gmail.com.movapp.tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.graphics.drawable.DrawableCompat

object SnackyUtils {

    fun tintDrawable(drawable: Drawable, @ColorInt color: Int): Drawable {
        var drawableMutate = drawable
        drawableMutate = DrawableCompat.wrap(drawableMutate)
        drawableMutate = drawableMutate.mutate()
        DrawableCompat.setTint(drawableMutate, color)
        return drawableMutate
    }

    fun makeTransparentDrawable(context: Context, width: Int, heigth: Int): Drawable {
        val conf = Bitmap.Config.ARGB_8888 // see other conf types
        val bmp = Bitmap.createBitmap(width, heigth, conf)
        return BitmapDrawable(context.resources, bmp)
    }
}