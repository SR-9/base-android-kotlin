package com.silkroad.network.core

import java.io.IOException

import com.silkroad.network.response.ProgressResponseBody
import okhttp3.Interceptor
import okhttp3.Response

/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 12/14/17
 * ____________________________________
 */

class ProgressInterceptor : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain : Interceptor.Chain) : Response {
		val originalResponse = chain.proceed(chain.request())
		val responseBuilder = originalResponse.newBuilder()

		val isAStream = originalResponse.header("content-type", "") == "application/octet-stream"
		if (isAStream) {
			responseBuilder.body(ProgressResponseBody(originalResponse.body()!!))
		} else {
			responseBuilder.body(originalResponse.body())
		}

		return responseBuilder.build()
	}
}
