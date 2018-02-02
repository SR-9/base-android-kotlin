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
		super.onCreate(savedInstanceState)
		setContentView(layoutId)
		onViewCreated()
	}
}
