package com.appupgrade.app_upgrade_android_kotlin_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appupgrade.app_upgrade_android_sdk.AppUpgrade
import com.appupgrade.app_upgrade_android_sdk.models.AppInfo

class MainActivity : AppCompatActivity() {

    private lateinit var appUpgrade: AppUpgrade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val xApiKey = "ZWY0ZDhjYjgtYThmMC00NTg5LWI0NmUtMjM5OWZkNjkzMzQ5"

        val appInfo = AppInfo(
            appId = "com.android.com",
            appName = "Wallpaper app",
            appVersion = "1.0.0",
            platform = "android",
            environment = "production"
        )

        appUpgrade = AppUpgrade()
        appUpgrade.checkForUpdates(this, xApiKey, appInfo)
    }
}