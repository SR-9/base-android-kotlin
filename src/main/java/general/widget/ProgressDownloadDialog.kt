package general.widget

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import general.network.eventBus.ProgressEventBus
import general.utils.ViewUtil

/**
 * ____________________________________
 *
 * Author: Hieu.TV - tvhieuit@gmail.com
 * Created: 12/14/17
 * ____________________________________
 */

class ProgressDownloadDialog @JvmOverloads constructor(context : Context, theme : Int = 0) : ProgressDialog(context, theme) {

	fun build() : ProgressDownloadDialog {
		setMessage("チケットを同期中")
		setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
		setCancelable(false)
		setProgressNumberFormat("")
		return this
	}

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		ViewUtil.setWindowFlag(window)
	}

	fun setDownloading(dataInfo : ProgressEventBus) {
		max = dataInfo.totalSize.toInt()
		progress = dataInfo.downloaded.toInt()
		setProgressNumberFormat("${dataInfo.downloaded.toInt() / 1000}/${dataInfo.totalSize.toInt() / 1000} Kb")
	}

}