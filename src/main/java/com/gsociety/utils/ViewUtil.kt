package com.gsociety.utils

import android.app.Activity
import android.view.View
import android.view.Window
import android.view.WindowManager


/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 12/20/17
 * ____________________________________
 */
object ViewUtil {
	fun getActionBarView(activity : Activity) : View {
		val window = activity.window
		val v = window.decorView
		val resId = activity.resources.getIdentifier("action_bar_container", "id", "android")
		return v.findViewById(resId)
	}

	fun setWindowFlag(window : Window) {
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
		window.decorView.systemUiVisibility = (
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				or View.SYSTEM_UI_FLAG_FULLSCREEN
				or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
				or View.SYSTEM_UI_FLAG_LOW_PROFILE
			)
	}
}