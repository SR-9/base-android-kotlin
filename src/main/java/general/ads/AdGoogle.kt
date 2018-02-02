package general.ads

import android.content.Context
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd


object teAdGoogle {
    val requestAd = AdRequest.Builder()
            .addTestDevice("9E45D85042B400FA38028D4D449EAC41")
            .addTestDevice("2B39CC415A03CA19CDBA8489E5FDA1DA")
            .build()

    fun showBanner(adView : AdView) {
        if (requestAd.isTestDevice(adView.context)) {
            return
        }
        adView.loadAd(requestAd)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                adView.visibility = View.VISIBLE
            }

            override fun onAdFailedToLoad(p0 : Int) {
                super.onAdFailedToLoad(p0)
                adView.visibility = View.GONE
            }
        }
    }

    fun showInterstitialAd(context : Context) {
        if (requestAd.isTestDevice(context)) {
            return
        }
        val interstitialAd = InterstitialAd(context)
        interstitialAd.adUnitId = "ca-app-pub-5423623055494031/8350744229"
        interstitialAd.loadAd(requestAd)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                interstitialAd.show()
            }
        }
    }
}