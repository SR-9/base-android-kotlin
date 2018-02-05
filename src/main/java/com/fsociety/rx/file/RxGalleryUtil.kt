package com.fsociety.rx.file

/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 11/30/17
 * ____________________________________
 */
/*

object RxGalleryUtil {
	private val TAG = RxFileUtil::class.java.simpleName

	fun readFilesFromDirect(file: File): Flowable<MutableList<File>> {
		Log.d(TAG, "readFilesFromDirect: " + file.path)
		return Flowable.fromCallable {
			if (file.isDirectory) {
				return@fromCallable mutableListOf<File>(*file.absoluteFile.listFiles())
			}
			return@fromCallable mutableListOf<File>()
		}
	}

	fun getPhotoss(context: Context): Flowable<MutableList<Media>> {
		return Flowable.fromCallable {

			val data = mutableListOf<Media>()

			val columns = arrayOf(
				MediaStore.Images.ImageColumns.TITLE,
				MediaStore.Images.ImageColumns.DATE_MODIFIED,
				MediaStore.Images.ImageColumns.DATA
			)

			val cursor = context.contentResolver.query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				columns,
				null, null, null
			)

			if (cursor.moveToFirst()) {
				do {
					data.add(Media(
						cursor.getString(0),
						Date(TimeUnit.SECONDS.toMillis(cursor.getLong(1))),
						cursor.getString(2)
					))
				} while (cursor.moveToNext())
			}

			cursor.close()

			return@fromCallable data
		}
	}

	fun getVideoss(context: Context): Flowable<MutableList<Media>> {
		return Flowable.fromCallable {

			val data = mutableListOf<Media>()

			val columns = arrayOf(
				MediaStore.Video.VideoColumns.TITLE,
				MediaStore.Video.VideoColumns.DATE_MODIFIED,
				MediaStore.Video.VideoColumns.DATA
			)

			val cursor = context.contentResolver.query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
				columns,
				null, null, null
			)

			if (cursor.moveToFirst()) {
				do {
					data.add(Media(
						cursor.getString(0),
						Date(TimeUnit.SECONDS.toMillis(cursor.getLong(1))),
						cursor.getString(2)
					))
				} while (cursor.moveToNext())
			}

			cursor.close()

			return@fromCallable data
		}
	}
}*/
