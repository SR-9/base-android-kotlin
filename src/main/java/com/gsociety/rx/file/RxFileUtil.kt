package com.gsociety.rx.file

import android.content.Context
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object RxFileUtil {

	fun copyFileFromAssets(context : Context, fileName : String, path : String) : Observable<Long> {
		return copyFile(context.assets.open(fileName), File("$path/$fileName"))
	}

	fun copyFile(inputStream : InputStream, file : File) : Observable<Long> {
		return Observable.create { emitter ->
			file.parentFile.mkdirs()
			file.createNewFile()
			/*input*/
			var outputStream : FileOutputStream? = null
			inputStream.buffered().use { input ->
				/*output*/
				outputStream = file.outputStream()
				outputStream?.buffered().use { output ->
					output?.let {
						emitter.onNext(input.copyTo(output, 10240))
					}
				}
			}
			outputStream?.close()
			inputStream.close()
			emitter.onComplete()
		}
	}

	fun copy(inputStream : InputStream, file : File) {
		file.parentFile.mkdirs()
		file.createNewFile()
		/*input*/
		var outputStream : FileOutputStream? = null
		inputStream.buffered().use { input ->
			/*output*/
			outputStream = file.outputStream()
			outputStream?.buffered().use { output ->
				output?.let {
					input.copyTo(output, 10240)
				}
			}
		}
		outputStream?.close()
		inputStream.close()
	}
}

