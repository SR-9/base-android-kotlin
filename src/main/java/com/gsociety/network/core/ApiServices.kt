package com.gsociety.network.core

import com.baseappication.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

open class ApiServices(private val url : String) {
	companion object {
		val DB_DATE_FORMAT = "yyyy-MM-dd kk:mm:ss"
	}

	private val _okHttpClient = getOkHttpClient()
	open var retrofit : Retrofit? = null
	get() {
		if (field == null) {
			field = getRetrofit(url)
		}
		return field!!
	}

	open fun getRetrofit(baseUrl : String) : Retrofit {
		println("--- init retrofit ---")
		val gson = GsonBuilder()
			.serializeNulls()
			.setDateFormat(DB_DATE_FORMAT)
			.create()
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(_okHttpClient)
			.callbackExecutor(Executors.newSingleThreadExecutor())
			.build()
	}

	private fun getOkHttpClient() : OkHttpClient {
		println("--- init okhttp ---")
		val logging = HttpLoggingInterceptor().apply {
			level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
		}
		return OkHttpClient.Builder()
			.addNetworkInterceptor(StethoInterceptor())
			.addNetworkInterceptor(ProgressInterceptor())
			.addInterceptor(logging)
			.readTimeout(1L, TimeUnit.MINUTES)
			.connectTimeout(1L, TimeUnit.MINUTES)
			.build()
	}

	fun cancel() {
		_okHttpClient.dispatcher().cancelAll()
	}
}
