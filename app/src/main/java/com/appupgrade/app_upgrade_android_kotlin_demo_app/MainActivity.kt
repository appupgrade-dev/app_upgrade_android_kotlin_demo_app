package com.appupgrade.app_upgrade_android_kotlin_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appupgrade.app_upgrade_android_sdk.AppUpgrade
import com.appupgrade.app_upgrade_android_sdk.models.AppInfo
import com.appupgrade.app_upgrade_android_sdk.models.PreferredAndroidMarket

class MainActivity : AppCompatActivity() {

    private lateinit var appUpgrade: AppUpgrade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val xApiKey = "ZWY0ZDhjYjgtYThmMC00NTg5LWI0NmUtMjM5OWZkNjkzMzQ5"

        val appInfo = AppInfo(
            appId = "com.snapdeal.main",
            appName = "Wallpaper app",
            appVersion = "1.0.0",
            platform = "android",
            environment = "production",
            appLanguage = "es", //Optional. Your app language code.
            // preferredAndroidMarket = PreferredAndroidMarket.HUAWEI, // or PreferredAndroidMarket.AMAZON or PreferredAndroidMarket.OTHER If not provided default is Google playstore. Optional
            // otherAndroidMarketUrl = "https://otherandroidmarketurl.com/app/id" // Required if preferredAndroidMarket is OTHER.
            // customAttributes = mapOf(
            //    "os" to 12,
            //    "country" to "IN",
            //    "build" to 100
            // )
        )

        appUpgrade = AppUpgrade()
        appUpgrade.checkForUpdates(this, xApiKey, appInfo)
    }
}