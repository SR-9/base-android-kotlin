package com.srbasektandroid.mvp


abstract class BasePresenter(private var viewCallback: Any?) {
	fun destroy() {
		viewCallback = null
	}
}