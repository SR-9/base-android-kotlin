package general.mvp

import android.app.Activity
import android.inputmethodservice.KeyboardView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import general.rx.keyboard.KeyboardUtil
import general.widget.Loading

abstract class BaseFragment : RxFragment() {
	protected abstract val layoutId : Int
	private val mLoading = Loading()
	private var mView : View? = null

	private var isViewCreated = false

	override fun onCreateView(inflater : LayoutInflater,
	                          container : ViewGroup?,
	                          savedInstanceState : Bundle?) : View {
		if (mView != null) return mView!!
		println("create fragment ${this.javaClass.simpleName}")
		mView = inflater.inflate(layoutId, container, false)
		return mView!!
	}

	override fun onPause() {
		KeyboardUtil.forceCloseKeyboard(mView)
		super.onPause()
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