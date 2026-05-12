package com.goal26.worldcup.ui.components

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

// Test Ad Unit IDs — replace with real ones in production
object AdUnitIds {
    const val BANNER = "ca-app-pub-6338497362113540/5349836117"
    const val INTERSTITIAL = "ca-app-pub-6338497362113540/2500695930"
    const val REWARDED = "ca-app-pub-6338497362113540/3757937280"
    const val APP_OPEN = "ca-app-pub-6338497362113540/8930372913"
    const val NATIVE = "ca-app-pub-6338497362113540/5349836117" // reuse banner for now
}

/**
 * Adaptive banner ad component.
 */
@Composable
fun AdBannerView(
    modifier: Modifier = Modifier,
    adUnitId: String = AdUnitIds.BANNER
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    this.adUnitId = adUnitId
                    loadAd(AdRequest.Builder().build())
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Interstitial ad helper — load and show with frequency cap.
 */
object InterstitialAdHelper {
    private var interstitialAd: InterstitialAd? = null
    private var lastShownTime: Long = 0
    private const val MIN_INTERVAL_MS = 120_000L // 2 minutes between interstitials
    private var navCount = 0
    private const val NAV_THRESHOLD = 3 // Show every 3rd nav

    fun load(context: Context) {
        InterstitialAd.load(
            context,
            AdUnitIds.INTERSTITIAL,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null
                }
            }
        )
    }

    fun tryShow(activity: Activity, onDismissed: () -> Unit = {}) {
        navCount++
        val now = System.currentTimeMillis()
        if (navCount >= NAV_THRESHOLD && now - lastShownTime > MIN_INTERVAL_MS) {
            interstitialAd?.let { ad ->
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        interstitialAd = null
                        lastShownTime = System.currentTimeMillis()
                        navCount = 0
                        load(activity)
                        onDismissed()
                    }
                }
                ad.show(activity)
            } ?: onDismissed()
        } else {
            onDismissed()
        }
    }
}

/**
 * Rewarded ad helper — for bonus trivia content.
 */
object RewardedAdHelper {
    private var rewardedAd: RewardedAd? = null

    fun load(context: Context) {
        RewardedAd.load(
            context,
            AdUnitIds.REWARDED,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    rewardedAd = null
                }
            }
        )
    }

    fun show(activity: Activity, onRewarded: () -> Unit, onDismissed: () -> Unit = {}) {
        rewardedAd?.let { ad ->
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    rewardedAd = null
                    load(activity)
                    onDismissed()
                }
            }
            ad.show(activity) { reward ->
                onRewarded()
            }
        }
    }

    fun isLoaded(): Boolean = rewardedAd != null
}

/**
 * App Open ad helper — shows on cold/warm start.
 */
object AppOpenAdHelper {
    private var appOpenAd: AppOpenAd? = null
    private var isShowingAd = false
    private var loadTime: Long = 0
    private const val MAX_AD_AGE_MS = 4 * 3600 * 1000L // 4 hours

    fun load(context: Context) {
        AppOpenAd.load(
            context,
            AdUnitIds.APP_OPEN,
            AdRequest.Builder().build(),
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    loadTime = System.currentTimeMillis()
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    appOpenAd = null
                }
            }
        )
    }

    fun showIfAvailable(activity: Activity) {
        if (isShowingAd) return
        val ad = appOpenAd ?: return
        if (System.currentTimeMillis() - loadTime > MAX_AD_AGE_MS) {
            appOpenAd = null
            load(activity)
            return
        }

        isShowingAd = true
        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                isShowingAd = false
                appOpenAd = null
                load(activity)
            }
            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                isShowingAd = false
                appOpenAd = null
                load(activity)
            }
        }
        ad.show(activity)
    }
}
