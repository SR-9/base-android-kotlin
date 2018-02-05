package com.fsociety.kotlin


/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 12/26/17
 * ____________________________________
 */

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.baseappication.BuildConfig
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
private val POOL_EXECUTOR = Executors.newCachedThreadPool()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(function : () -> Unit) {
	IO_EXECUTOR.execute(function)
}

fun poolThread(function : () -> Unit) {
	POOL_EXECUTOR.execute(function)
}


/** Extensions for simpler launching of Activities */
@SuppressLint("ObsoleteSdkInt")
inline fun <reified T : Any> Activity.launchActivity(
	requestCode : Int = -1,
	options : Bundle? = null,
	noinline init : Intent.() -> Unit = {}) {

	val intent = newIntent<T>(this)
	intent.init()
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
		startActivityForResult(intent, requestCode, options)
	} else {
		startActivityForResult(intent, requestCode)
	}
}

@SuppressLint("ObsoleteSdkInt")
inline fun <reified T : Any> Context.launchActivity(
	options : Bundle? = null,
	noinline init : Intent.() -> Unit = {}) {

	val intent = newIntent<T>(this)
	intent.init()
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
		startActivity(intent, options)
	} else {
		startActivity(intent)
	}
}

inline fun <reified T : Any> newIntent(context : Context) : Intent = Intent(context, T::class.java)

/** Fragment transaction */
inline fun FragmentManager.inTransaction(func : FragmentTransaction.() -> Unit) {
	val fragmentTransaction = this.beginTransaction()
	fragmentTransaction.func()
	fragmentTransaction.commitAllowingStateLoss()
	//this.executePendingTransactions()
}

/** SharedPreference */
fun Context.sharedPreferences() = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)!!
fun Activity.sharedPreferences() = (this as Context).sharedPreferences()
fun Fragment.sharedPreferences() = context.sharedPreferences()
