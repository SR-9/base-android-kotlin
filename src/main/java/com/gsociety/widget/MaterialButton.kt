package com.gsociety.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.baseappication.R
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.rxkotlin.subscribeBy


/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 2/3/18
 * ____________________________________
 */


class MaterialButton @JvmOverloads constructor(
	context : Context,
	attributeSet : AttributeSet? = null,
	defAttrStyle : Int = 0
) : AppCompatButton(context, attributeSet, defAttrStyle) {

	private var _cornerRadius : Float = -1f
	private var _stroke : Int = 1
	private var _strokeColor : Int = Color.GRAY

	init {
		val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialView)
		_cornerRadius = typeArray.getDimensionPixelSize(R.styleable.MaterialView_cornerRadius, -1).toFloat()
		_stroke = typeArray.getDimensionPixelSize(R.styleable.MaterialView_stroke, 1)
		_strokeColor = typeArray.getColor(R.styleable.MaterialView_strokeColor, Color.GRAY)
		typeArray.recycle()

		isClickable = true
		if(background == null) background = null
	}

	override fun setBackground(background : Drawable?) {
		RxView.layoutChanges(this)
			.bindToLifecycle(this)
			.subscribeBy {
				val bg = when (background) {
					is GradientDrawable -> {
						background
					}
					is ColorDrawable -> {
						GradientDrawable().apply {
							colorFilter = background.colorFilter
							cornerRadius = if (_cornerRadius >= 0) _cornerRadius else (measuredHeight / 2f)
							setStroke(_stroke, _strokeColor)
							this.setColor(background.color) // transparent cannot ripple
						}
					}
					else -> {
						GradientDrawable().apply {
							colorFilter = background?.colorFilter
							cornerRadius = if (_cornerRadius >= 0) _cornerRadius else (measuredHeight / 2f)
							setStroke(_stroke, _strokeColor)
							this.setColor(Color.WHITE) // transparent cannot ripple
						}
					}
				}
				val colorStateListRipple = ColorStateList(
					arrayOf(intArrayOf(0)),
					intArrayOf(0x30000000) // ripple color
				)
				super.setBackground(RippleDrawable(colorStateListRipple, bg, bg))
			}

	}

}