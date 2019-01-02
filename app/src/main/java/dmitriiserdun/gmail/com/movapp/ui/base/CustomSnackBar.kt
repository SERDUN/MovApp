package dmitriiserdun.gmail.com.movapp.ui.base

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.support.annotation.IntRange
import android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.R
import dmitriiserdun.gmail.com.movapp.tools.SnackyUtils


class CustomSnackBar private constructor(private val builder: Builder) {
    enum class Type(@param:ColorInt val color: Int?, @param:DrawableRes private val iconResId: Int?, @param:ColorInt val standardTextColor: Int?) {
        DEFAULT(null, null, null), SUCCESS(Color.parseColor("#388E3C"),
                R.drawable.ic_check_black_24dp,
                Color.WHITE),
        ERROR(Color.parseColor("#D50000"),
                R.drawable.ic_clear_black_24dp,
                Color.WHITE),
        INFO(Color.parseColor("#3F51B5"),
                R.drawable.ic_info_outline_black_24dp,
                Color.WHITE),
        WARNING(Color.parseColor("#FFA900"),
                R.drawable.ic_error_outline_black_24dp,
                Color.BLACK);

        fun getIcon(context: Context): Drawable? {
            if (iconResId == null) return null
            var drawable = ContextCompat.getDrawable(context, iconResId)
            if (drawable != null) {
                drawable = SnackyUtils.tintDrawable(drawable, standardTextColor!!)
            }
            return drawable
        }
    }

    private fun make(): Snackbar {

        val snackbar = Snackbar.make(builder.view!!, builder.text, builder.duration)

        if (builder.actionClickListener != null || builder.actionText != null) {
            if (builder.actionClickListener == null)
                builder.actionClickListener
            snackbar.setAction(builder.actionText, builder.actionClickListener)
            if (builder.actionTextColor == null) builder.actionTextColor = builder.type.standardTextColor
            if (builder.actionTextColors != null)
                snackbar.setActionTextColor(builder.actionTextColors)
            else if (builder.actionTextColor != null) snackbar.setActionTextColor(builder.actionTextColor!!)

        }

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

        if (builder.backgroundColor == null) builder.backgroundColor = builder.type.color
        if (builder.backgroundColor != null) snackbarLayout.setBackgroundColor(builder.backgroundColor!!)

        val actionText = snackbarLayout.findViewById<TextView>(android.support.design.R.id.snackbar_action)
        if (builder.actionTextSize != null) {
            if (builder.actionTextSizeUnit != null)
                actionText.setTextSize(builder.actionTextSizeUnit!!, builder.actionTextSize!!)
            else
                actionText.textSize = builder.actionTextSize!!
        }
        var actionTextTypeface: Typeface? = actionText.typeface
        if (builder.actionTextTypeface != null)
            actionTextTypeface = builder.actionTextTypeface
        if (builder.actionTextTypefaceStyle != null) {
            actionText.setTypeface(actionTextTypeface, builder.actionTextTypefaceStyle!!)
        } else {
            actionText.typeface = actionTextTypeface
        }


        val text = snackbarLayout.findViewById<TextView>(android.support.design.R.id.snackbar_text)

        if (builder.textSize != null) {
            if (builder.textSizeUnit != null)
                text.setTextSize(builder.textSizeUnit!!, builder.textSize!!)
            else
                text.textSize = builder.textSize!!
        }

        var textTypeface: Typeface? = text.typeface
        if (builder.textTypeface != null)
            textTypeface = builder.textTypeface
        if (builder.textTypefaceStyle != null) {
            text.setTypeface(textTypeface, builder.textTypefaceStyle!!)
        } else {
            text.typeface = textTypeface
        }


        if (builder.textColor == null) builder.textColor = builder.type.standardTextColor
        if (builder.textColors != null)
            text.setTextColor(builder.textColors)
        else if (builder.textColor != null) text.setTextColor(builder.textColor!!)
        text.maxLines = builder.maxLines
        text.gravity = if (builder.centerText) Gravity.CENTER else Gravity.CENTER_VERTICAL
        if (builder.centerText)
            text.textAlignment = View.TEXT_ALIGNMENT_CENTER

        if (builder.icon == null) builder.icon = builder.type.getIcon(builder.view!!.context)
        if (builder.icon != null) {
            var transparentHelperDrawable: Drawable? = null
            if (builder.centerText && TextUtils.isEmpty(builder.actionText)) {
                transparentHelperDrawable = SnackyUtils.makeTransparentDrawable(builder.view!!.context,
                        builder.icon!!.intrinsicWidth,
                        builder.icon!!.intrinsicHeight)
            }
            text.setCompoundDrawablesWithIntrinsicBounds(builder.icon, null, transparentHelperDrawable, null)
            text.compoundDrawablePadding = text.resources
                    .getDimensionPixelOffset(R.dimen.snacky_icon_padding)
        }


        return snackbar
    }

    @RestrictTo(LIBRARY_GROUP)
    @IntDef(LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG)
    @IntRange(from = 1)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Duration


    class Builder {

        internal var view: View? = null
        internal var type = Type.DEFAULT
        internal var duration = Snackbar.LENGTH_SHORT
        internal var text: CharSequence = ""
        internal var textResId = 0
        internal var textColor: Int? = null
        internal var textColors: ColorStateList? = null
        internal var textSizeUnit: Int? = null
        internal var textSize: Float? = null
        internal var textTypefaceStyle: Int? = null
        internal var textTypeface: Typeface? = null
        internal var actionTextSizeUnit: Int? = null
        internal var actionTextSize: Float? = null
        internal var actionText: CharSequence = ""
        internal var actionTextResId = 0
        internal var actionTextTypefaceStyle: Int? = null
        internal var actionTextTypeface: Typeface? = null
        internal var actionClickListener: View.OnClickListener? = null
        internal var actionTextColor: Int? = null
        internal var actionTextColors: ColorStateList? = null
        internal var maxLines = Integer.MAX_VALUE
        internal var centerText = false
        internal var icon: Drawable? = null
        internal var iconResId = 0
        internal var backgroundColor: Int? = null

        fun setActivity(activity: Activity): Builder {
            return setView((activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0))
        }

        fun setView(view: View): Builder {
            this.view = view
            return this
        }

        fun setText(@StringRes resId: Int): Builder {
            this.textResId = resId
            return this
        }

        fun setText(text: CharSequence): Builder {
            this.textResId = 0
            this.text = text
            return this
        }

        fun setTextColor(@ColorInt color: Int): Builder {
            this.textColor = color
            return this
        }

        fun setTextColor(colorStateList: ColorStateList): Builder {
            this.textColor = null
            this.textColors = colorStateList
            return this
        }

        fun setTextSize(textSize: Float): Builder {
            this.textSizeUnit = null
            this.textSize = textSize
            return this
        }

        fun setTextSize(unit: Int, textSize: Float): Builder {
            this.textSizeUnit = unit
            this.textSize = textSize
            return this
        }

        fun setTextTypeface(typeface: Typeface): Builder {
            this.textTypeface = typeface
            return this
        }

        fun setTextTypefaceStyle(style: Int): Builder {
            this.textTypefaceStyle = style
            return this
        }

        fun centerText(): Builder {
            this.centerText = true
            return this
        }

        fun setActionTextColor(colorStateList: ColorStateList): Builder {
            this.actionTextColor = null
            this.actionTextColors = colorStateList
            return this
        }

        fun setActionTextColor(@ColorInt color: Int): Builder {
            this.actionTextColor = color
            return this
        }

        fun setActionText(@StringRes resId: Int): Builder {
            this.actionTextResId = resId
            return this
        }

        fun setActionText(text: CharSequence): Builder {
            this.textResId = 0
            this.actionText = text
            return this
        }

        fun setActionTextSize(textSize: Float): Builder {
            this.actionTextSizeUnit = null
            this.actionTextSize = textSize
            return this
        }

        fun setActionTextSize(unit: Int, textSize: Float): Builder {
            this.actionTextSizeUnit = unit
            this.actionTextSize = textSize
            return this
        }

        fun setActionTextTypeface(typeface: Typeface): Builder {
            this.actionTextTypeface = typeface
            return this
        }


        fun setActionTextTypefaceStyle(style: Int): Builder {
            this.actionTextTypefaceStyle = style
            return this
        }

        fun setActionClickListener(listener: View.OnClickListener): Builder {
            this.actionClickListener = listener
            return this
        }

        fun setMaxLines(maxLines: Int): Builder {
            this.maxLines = maxLines
            return this
        }

        fun setDuration(@Duration duration: Int): Builder {
            this.duration = duration
            return this
        }

        fun setIcon(@DrawableRes resId: Int): Builder {
            this.iconResId = resId
            return this
        }

        fun setIcon(drawable: Drawable): Builder {
            this.icon = drawable
            return this
        }

        fun setBackgroundColor(@ColorInt color: Int): Builder {
            this.backgroundColor = color
            return this
        }

        fun build(): Snackbar {
            return make()
        }

        fun success(): Snackbar {
            type = Type.SUCCESS
            return make()
        }

        fun info(): Snackbar {
            type = Type.INFO
            return make()
        }

        fun warning(): Snackbar {
            type = Type.WARNING
            return make()
        }

        fun error(): Snackbar {
            type = Type.ERROR
            return make()
        }

        private fun make(): Snackbar {
            if (view == null)
                throw IllegalStateException("Snacky Error: You must set an Activity or a View before making a snack")
            if (textResId != 0)
                text = view!!.resources
                        .getText(textResId)
            if (actionTextResId != 0)
                actionText = view!!.resources
                        .getText(actionTextResId)
            if (iconResId != 0) icon = App.instance.getDrawable(iconResId)
            return CustomSnackBar(this).make()
        }
    }

    companion object {

        fun builder(): Builder {
            return Builder()
        }

        const val LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE
        const val LENGTH_SHORT = Snackbar.LENGTH_SHORT
        const val LENGTH_LONG = Snackbar.LENGTH_LONG
    }
}
