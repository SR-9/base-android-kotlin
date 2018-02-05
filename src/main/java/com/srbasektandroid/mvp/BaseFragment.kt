package com.srbasektandroid.mvp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.srbasektandroid.widget.Loading

abstract class BaseFragment : RxFragment() {
	protected abstract val layoutId : Int
	private val mLoading = Loading()
	private var mView : View? = null

	private var isViewCreated = false

	override fun onCreateView(inflater : LayoutInflater,
	                          container : ViewGroup?,
	                          savedInstanceState : Bundle?) : View {
		if (mView != null) return mView!!
		mView = inflater.inflate(layoutId, container, false)
		return mView!!
	}

	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if(!isViewCreated) {
			isViewCreated = true
			onViewCreated()
		}
	}

	open fun onViewCreated() {}
	open fun showLoading() = mLoading.show((context as Activity).fragmentManager, System.currentTimeMillis().toString())
	open fun hideLoading() = mLoading.dismiss()


}