package com.gsociety.mvp

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.gsociety.widget.Loading

abstract class BaseActivity : RxAppCompatActivity() {


	protected abstract val layoutId : Int
	private val mLoading = Loading()

	open fun onViewCreated() {}
	open fun showLoading() = mLoading.show(fragmentManager, System.currentTimeMillis().toString())
	open fun hideLoading() = mLoading.dismiss()

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layoutId)
		onViewCreated()
	}
}
