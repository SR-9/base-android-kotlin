package com.fsociety.mvp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.baseappication.R
import com.fsociety.widget.Loading
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {


	protected abstract val layoutId : Any
	private val mLoading = Loading()

	open fun onViewCreated() {}
	open fun showLoading() = mLoading.show(fragmentManager, System.currentTimeMillis().toString())
	open fun hideLoading() = mLoading.dismiss()

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		when (layoutId) {
			is View -> setContentView(layoutId as View)
			is Int -> setContentView(layoutId as Int)
			else -> println("unknown layout")
		}
		setupActionbar()
		onViewCreated()
	}

	private fun setupActionbar() {
		val actionBar = supportActionBar
		actionBar?.let {
			title = "Fuck Society"
		}
	}

	override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onContextItemSelected(item : MenuItem?) : Boolean {
		return super.onContextItemSelected(item)
	}
}
