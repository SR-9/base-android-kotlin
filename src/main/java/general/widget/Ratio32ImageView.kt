package general.widget

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

open class Ratio32ImageView @JvmOverloads constructor(context : Context?, attrs : AttributeSet? = null, defStyleAttr : Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec : Int, heightMeasureSpec : Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth * 2 / 3)
    }
}