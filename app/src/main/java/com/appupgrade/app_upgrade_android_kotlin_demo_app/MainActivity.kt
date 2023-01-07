package com.appupgrade.app_upgrade_android_kotlin_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var appUpgrade: AppUpgrade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appUpgrade = AppUpgrade()
        appUpgrade.checkForUpdates(this)
    }
}