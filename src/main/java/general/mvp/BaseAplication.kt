package general.mvp

import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric




/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 2/2/18
 * ____________________________________
 */

class BaseAplication : MultiDexApplication() {
	override fun onCreate() {
		super.onCreate()
		Fabric.with(this, Crashlytics())
	}
}