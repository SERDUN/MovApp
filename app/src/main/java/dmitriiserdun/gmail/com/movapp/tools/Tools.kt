package dmitriiserdun.gmail.com.movapp.tools

import android.content.Context
import android.util.Log
import android.util.TypedValue

object Tools {
    fun convertPixelsToDp(px: Float, context: Context): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.resources.displayMetrics)

    }

    fun convertDpToPixel(dp: Int, context: Context): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics)


    }

    fun convertSpToPixel(sp: Float, context: Context): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().displayMetrics)

    }


}