package com.srbasektandroid.utils

import android.graphics.Point
import com.srbasektandroid.mvp.BaseActivity


/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 1/2/18
 * ____________________________________
 */

object DisplayUtil {

	val screen = Point()

	fun initScreenSize(activity : BaseActivity) {
		activity.windowManager.defaultDisplay.getSize(screen)
	}
}