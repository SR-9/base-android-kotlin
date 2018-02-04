package general.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.baseappication.R


/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 2/3/18
 * ____________________________________
 */


class MaterialTextView @JvmOverloads constructor(
	context : Context,
	attributeSet : AttributeSet? = null,
	defAttrStyle : Int = 0
) : AppCompatTextView(context, attributeSet, defAttrStyle) {

	private var _cornerRadius : Float = 0f
	private var _stroke : Int = 5
	private var _strokeColor : Int = Color.RED

	init {
		val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialTextView)
		_cornerRadius = typeArray.getDimensionPixelSize(R.styleable.MaterialTextView_cornerRadius, _cornerRadius.toInt()).toFloat()
		_stroke = typeArray.getDimensionPixelSize(R.styleable.MaterialTextView_stroke, _stroke)
		_strokeColor = typeArray.getColor(R.styleable.MaterialTextView_strokeColor, _strokeColor)
		typeArray.recycle()
	}

	override fun setBackground(background : Drawable?) {
		if (background != null) println("tran van hieu check ${background::class.java.simpleName}")
		val bg = when (background) {
			is ColorDrawable -> {
				println("stroke $_stroke   $_strokeColor")
				GradientDrawable().apply {
					colorFilter = background.colorFilter
					cornerRadius = _cornerRadius
					setStroke(_stroke, _strokeColor)
					this.setColor(background.color) // transparent cannot ripple
				}
			}
			else -> {
				GradientDrawable().apply {
					colorFilter = background?.colorFilter
					cornerRadius = _cornerRadius
					setStroke(_stroke, _strokeColor)
					this.setColor(Color.TRANSPARENT) // transparent cannot ripple
				}
			}
		}
		super.setBackground(bg)
	}

}