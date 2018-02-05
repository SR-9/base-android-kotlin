package com.srbasektandroid.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet

import com.baseappication.R
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.rxkotlin.subscribeBy

/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 2/5/18
 * ____________________________________
 */

class MaterialEditText : AppCompatEditText {

	private var _cornerRadius = -1f
	private var _stroke = 1
	private var _strokeColor = Color.GRAY


	constructor(context : Context) : super(context) {
		setupFirstBackground(context, null, 0)
	}

	constructor(context : Context, attrs : AttributeSet) : super(context, attrs) {
		setupFirstBackground(context, attrs, 0)
	}

	constructor(context : Context, attrs : AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr) {
		setupFirstBackground(context, attrs, defStyleAttr)
	}

	private fun setupFirstBackground(context : Context, attrs : AttributeSet?, defStyleAttr : Int) {
		if (attrs != null) {
			val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialView, defStyleAttr, 0)
			_cornerRadius = typeArray.getDimensionPixelSize(R.styleable.MaterialView_cornerRadius, -1).toFloat()
			_stroke = typeArray.getDimensionPixelSize(R.styleable.MaterialView_stroke, 1)
			_strokeColor = typeArray.getColor(R.styleable.MaterialView_strokeColor, Color.GRAY)
			typeArray.recycle()
		}
	}

	override fun setBackground(background : Drawable?) {
		RxView.layoutChanges(this)
			.bindToLifecycle(this)
			.subscribeBy {
				val changeBg = when (background) {
					is GradientDrawable -> {
						background
					}
					is ColorDrawable -> {
						val bg = GradientDrawable()
						if (_cornerRadius >= 0) {
							bg.cornerRadius = _cornerRadius
						} else {
							bg.cornerRadius = (measuredHeight / 2f)
						}
						bg.setColor(background.color)
						bg.setStroke(_stroke, _strokeColor)
						bg
					}
					else -> {
						println("vao = 3")
						val bg = GradientDrawable()
						if (_cornerRadius >= 0) {
							bg.cornerRadius = _cornerRadius
						} else {
							bg.cornerRadius = (measuredHeight / 2f)
						}
						bg.setStroke(_stroke, _strokeColor)
						bg
					}
				}

				super.setBackground(changeBg)
			}
	}
}
