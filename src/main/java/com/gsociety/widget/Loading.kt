package com.gsociety.widget

import android.app.DialogFragment
import android.app.FragmentManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.gsociety.utils.ViewUtil


open class Loading : DialogFragment() {

	/** SINGLETON OBJECT */
	private object Holder {
		val INSTANCE = Loading()
	}

	companion object {
		val instance : Loading by lazy { Holder.INSTANCE }
	}
	/** ------------------- */

	private var isShown = false

	override fun onCreateView(inflater : LayoutInflater?, container : ViewGroup?, savedInstanceState : Bundle?) : View {
		return ProgressBar(inflater?.context)
	}

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		retainInstance = true
	}

	override fun onViewCreated(view : View?, b : Bundle?) {
		setWindowFlag()
		dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
		dialog.setCanceledOnTouchOutside(false)
		dialog.setCancelable(false)
		dialog.setOnKeyListener({ _, _, _ -> true })
	}

	override fun show(manager : FragmentManager?, tag : String?) {
		if (!isAdded && !isShown) {
			isShown = true
			super.show(manager, tag)
		}
	}

	override fun dismiss() {
		isShown = false
		super.dismiss()
	}

	private fun setWindowFlag() {
		ViewUtil.setWindowFlag(dialog.window)
	}
}