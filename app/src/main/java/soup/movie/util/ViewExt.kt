package soup.movie.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import soup.movie.data.helper.today
import soup.movie.util.Interpolators.ALPHA_IN
import soup.movie.util.Interpolators.ALPHA_OUT
import soup.movie.util.glide.GlideApp
import soup.movie.util.glide.IntegerVersionSignature
import soup.widget.util.OnDebounceClickListener

/** View */

typealias OnClickListener = (View) -> Unit

fun View.setOnDebounceClickListener(listener: OnClickListener?) {
    if (listener == null) {
        setOnClickListener(null)
    } else {
        setOnClickListener(OnDebounceClickListener {
            if (it != null) listener.invoke(it)
        })
    }
}

fun View.blockExtraTouchEvents() = setOnTouchListener { _, _ ->  true }

@Suppress("UNCHECKED_CAST")
fun <T : View> inflate(context: Context, @LayoutRes resource: Int): T =
        View.inflate(context, resource, null) as T

@Suppress("UNCHECKED_CAST")
fun <T : View> inflate(context: Context, @LayoutRes resource: Int, root: ViewGroup): T =
        View.inflate(context, resource, root) as T

inline fun View.setVisibleIf(predicate: () -> Boolean) {
    visibility = if (predicate()) View.VISIBLE else View.GONE
}

inline fun View.setGoneIf(predicate: () -> Boolean) {
    visibility = if (predicate()) View.GONE else View.VISIBLE
}

@BindingAdapter("android:visibleIf")
fun View.setVisibleIfInXml(predicate: Boolean) {
    visibility = if (predicate) View.VISIBLE else View.GONE
}

@BindingAdapter("android:goneIf")
fun View.setGoneIfInXml(predicate: Boolean) {
    visibility = if (predicate) View.VISIBLE else View.GONE
}

fun View.animateHide(animate: Boolean) {
    animate().cancel()
    if (!animate) {
        alpha = 0f
        visibility = View.INVISIBLE
        return
    }
    animate()
            .alpha(0f)
            .setDuration(160)
            .setStartDelay(0)
            .setInterpolator(ALPHA_OUT)
            .withEndAction { visibility = View.INVISIBLE }
}

fun View.animateShow(animate: Boolean) {
    animate().cancel()
    visibility = View.VISIBLE
    if (!animate) {
        alpha = 1f
        return
    }
    alpha = 0f
    animate()
            .alpha(1f)
            .setDuration(320)
            .setInterpolator(ALPHA_IN)
            .setStartDelay(50)
            // We need to clean up any pending end action from animateHide if we call
            // both hide and show in the same frame before the animation actually gets started.
            // cancel() doesn't really remove the end action.
            .withEndAction(null)
}

fun View.setBackgroundColorResource(@ColorRes resId: Int) {
    setBackgroundColor(context.getColorCompat(resId))
}

@BindingAdapter("android:backgroundResource")
fun View.setBackgroundResourceInXml(resId: Int) {
    setBackgroundResource(resId)
}

@BindingAdapter("android:selected")
fun View.setSelectedInXml(selected: Boolean) {
    isSelected = selected
}

infix fun View.with(tag: String): Pair<View, String> {
    return Pair.create(this, tag)
}

infix fun View.with(@StringRes tagId: Int): Pair<View, String> {
    return Pair.create(this, context.getString(tagId))
}

@BindingAdapter("android:backgroundTint")
fun View.setBackgroundTint(@ColorInt color: Int) {
    backgroundTintList = ColorStateList.valueOf(color)
}

/** ViewGroup */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

/** ImageView */

@BindingAdapter("android:tint")
fun ImageView.setImageTint(@ColorInt color: Int) {
    imageTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("android:srcUrl")
fun ImageView.loadAsync(url: String) {
    GlideApp.with(context)
            .load(url)
            .signature(IntegerVersionSignature(today().run { year * 100 + monthValue }))
            .priority(Priority.IMMEDIATE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}

fun ImageView.loadAsync(url: String, requestListener: RequestListener<Drawable>) {
    GlideApp.with(context)
            .load(url)
            .signature(IntegerVersionSignature(today().run { year * 100 + monthValue }))
            .listener(requestListener)
            .priority(Priority.IMMEDIATE)
            .into(this)
}

/* ContentLoadingProgressBar */

inline fun ContentLoadingProgressBar.showIf(predicate: () -> Boolean) {
    if (predicate()) show() else hide()
}
