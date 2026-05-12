package com.goal26.worldcup

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.goal26.worldcup.ui.components.AppOpenAdHelper
import com.goal26.worldcup.ui.components.InterstitialAdHelper
import com.goal26.worldcup.ui.components.RewardedAdHelper
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Goal26App : Application() {

    private var currentActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)

        // Preload all ad formats
        AppOpenAdHelper.load(this)
        InterstitialAdHelper.load(this)
        RewardedAdHelper.load(this)

        // Track current activity for app open ads
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
            override fun onActivityStarted(activity: Activity) { currentActivity = activity }
            override fun onActivityResumed(activity: Activity) { currentActivity = activity }
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {
                if (currentActivity === activity) currentActivity = null
            }
        })

        // Show app open ad when app comes to foreground
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                currentActivity?.let { AppOpenAdHelper.showIfAvailable(it) }
            }
        })
    }
}
