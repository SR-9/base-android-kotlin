package general.mvp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import com.baseappication.BuildConfig
import com.baseappication.R
import com.google.android.gms.ads.MobileAds
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import general.rx.keyboard.RxKeyboardUtil
import general.utils.ViewUtil
import general.widget.Loading
import io.reactivex.rxkotlin.subscribeBy

abstract class BaseActivity : RxAppCompatActivity() {


	protected abstract val layoutId : Int
	private val mLoading = Loading()

	open fun onViewCreated() {}
	open fun showLoading() = mLoading.show(fragmentManager, System.currentTimeMillis().toString())
	open fun hideLoading() = mLoading.dismiss()

	override fun onCreate(savedInstanceState : Bundle?) {
		MobileAds.initialize(this, "ca-app-pub-5423623055494031~4774816517")
//		setWindowFlag()
		super.onCreate(savedInstanceState)
		setContentView(layoutId)
		onViewCreated()
	}

	private fun setWindowFlag() {
		ViewUtil.setWindowFlag(window)
	}

	override fun onWindowFocusChanged(hasFocus : Boolean) {
		super.onWindowFocusChanged(hasFocus)
//		setWindowFlag()
	}

	override fun onResume() {
		super.onResume()
//		keyboardHandler()
	}

	private fun keyboardHandler() {
		RxKeyboardUtil().create(window.decorView.rootView)
			.bindToLifecycle(this)
			.subscribeBy{
				if(it < 200) {
					ViewUtil.setWindowFlag(window)
				}
			}
	}

	fun setupSlidingAnimator() {
		overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
	}
}
