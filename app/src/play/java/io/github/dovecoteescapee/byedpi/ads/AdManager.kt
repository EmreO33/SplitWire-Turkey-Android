package io.github.dovecoteescapee.byedpi.ads

import android.app.Activity
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

// Play edition: shows an AdMob banner.
//
// NOTE: the IDs below are Google's official TEST ad units, so the app shows
// test ads and never risks an AdMob policy strike during development.
// Replace BANNER_AD_UNIT_ID (and the APPLICATION_ID in src/play/AndroidManifest.xml)
// with your real AdMob IDs before publishing.
object AdManager {
    private const val BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"

    private var initialized = false

    fun attachBanner(activity: Activity, container: ViewGroup) {
        if (!initialized) {
            MobileAds.initialize(activity) {}
            initialized = true
        }

        val adView = AdView(activity).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = BANNER_AD_UNIT_ID
        }
        container.removeAllViews()
        container.addView(adView)
        adView.loadAd(AdRequest.Builder().build())
    }
}
