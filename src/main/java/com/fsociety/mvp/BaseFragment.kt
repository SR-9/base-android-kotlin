package com.fsociety.mvp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fsociety.widget.Loading
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class BaseFragment : RxFragment() {
	protected abstract val layoutId : Any
	private val mLoading = Loading()
	private var mView : View? = null

	private var isViewCreated = false

	override fun onCreateView(inflater : LayoutInflater,
	                          container : ViewGroup?,
	                          savedInstanceState : Bundle?) : View {

		mView = mView ?: when (layoutId) {
			is View -> layoutId as View
			is Int -> inflater.inflate(layoutId as Int, container, false)
			else -> throw Exception("Unknown layout")
		}

		return mView!!
	}

	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (!isViewCreated) {
			isViewCreated = true
			onViewCreated()
		}
	}

	open fun onViewCreated() {}
	open fun showLoading() = mLoading.show((context as Activity).fragmentManager, System.currentTimeMillis().toString())
	open fun hideLoading() = mLoading.dismiss()


}