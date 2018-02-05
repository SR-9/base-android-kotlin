package com.srbasektandroid.network.response

import okhttp3.ResponseBody
import okio.*
import java.io.IOException

/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 12/14/17
 * ____________________________________
 */

class ProgressResponseBody(private val responseBody : ResponseBody) : ResponseBody() {

	private var bufferedSource : BufferedSource? = null

	override fun contentLength() = responseBody.contentLength()

	override fun contentType() = responseBody.contentType()

	override fun source() : BufferedSource {
		if (bufferedSource == null) {
			bufferedSource = Okio.buffer(source(responseBody.source()))
		}
		return bufferedSource!!
	}

	private fun source(source : Source) : Source {
		return object : ForwardingSource(source) {
			internal var totalBytesRead = 0L
			@Throws(IOException::class)
			override fun read(sink : Buffer, byteCount : Long) : Long {
				val bytesRead = super.read(sink, byteCount)
				totalBytesRead += if (bytesRead != (-1).toLong()) bytesRead else 0.toLong()
				//EventBus.getDefault().post(ProgressEventBus(downloaded = totalBytesRead, totalSize = contentLength()))
				return bytesRead
			}
		}

	}
}