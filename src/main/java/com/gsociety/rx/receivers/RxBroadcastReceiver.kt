package com.gsociety.rx.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Looper
import android.support.v4.content.LocalBroadcastManager
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.annotations.NonNull
import io.reactivex.internal.operators.observable.ObservableCreate

object RxBroadcastReceiver {

	/**
	 *
	 * @param context
	 * @param intentFilter
	 * @return
	 */

	fun localReceiver(@NonNull context : Context, @NonNull intentFilter : IntentFilter) : Observable<Intent> {
		return Observable.create { emitter ->
			val receiver = object : BroadcastReceiver() {
				override fun onReceive(context : Context, intent : Intent) {
					emitter.onNext(intent)
				}
			}
			println("register ---------------- ${Gson().toJson(intentFilter)}")
			LocalBroadcastManager.getInstance(context).registerReceiver(receiver, intentFilter)
			emitter.setCancellable {
				println("unregister ---------------- ${Gson().toJson(intentFilter)}")
				if (Looper.getMainLooper() == Looper.myLooper()) {
					LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
				} else {
					val inner = mainThread().createWorker()
					inner.schedule {
						LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
						inner.dispose()
					}
				}
			}
		}
	}

	fun receiver(@NonNull context : Context, @NonNull intentFilter : IntentFilter) : Observable<Intent> {
		return ObservableCreate {
			val receiver = object : BroadcastReceiver() {
				override fun onReceive(context : Context, intent : Intent) {
					//val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
					it.onNext(intent)
				}
			}

			context.registerReceiver(receiver, intentFilter)

			it.setCancellable {
				if (Looper.getMainLooper() == Looper.myLooper()) {
					context.unregisterReceiver(receiver)
				} else {
					val inner = mainThread().createWorker()
					inner.schedule {
						context.unregisterReceiver(receiver)
						inner.dispose()
					}
				}
			}
		}
	}
}