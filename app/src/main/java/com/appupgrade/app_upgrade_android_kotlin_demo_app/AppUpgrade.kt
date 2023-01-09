package com.appupgrade.app_upgrade_android_kotlin_demo_app

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Button
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class AppUpgrade {
    fun checkForUpdates(parentActivity: Activity) {
        // Set the API host
        val host = "appupgrade.dev"

        // Set the API key and other headers
        val apiKey = "ZWY0ZDhjYjgtYThmMC00NTg5LWI0NmUtMjM5OWZkNjkzMzQ5"
        val headers = Headers.Builder().add("x-api-key", apiKey).build()

        // Set the request parameters
        val appName = "Wallpaper app"
        val appVersion = "1.0.0"
        val platform = "android"
        val environment = "production"

        val url = HttpUrl.Builder()
            .scheme("https")
            .host(host)
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("versions")
            .addPathSegment("check")
            .addQueryParameter("app_name", appName)
            .addQueryParameter("app_version", appVersion)
            .addQueryParameter("platform", platform)
            .addQueryParameter("environment", environment)
            .build()

        // Create the request
        val request = Request.Builder()
            .url(url)
            .headers(headers)
            .get()
            .build()

        // Send the request and process the response
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the failure
                Log.i("App Upgrade: ", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 200) {
                    val responseBody = response.body?.string()
                    val responseJson = JSONObject(responseBody)
                    Log.i("App Upgrade: ", response.toString())

                    if (responseJson.getBoolean("found")) {
                        Log.i("App Upgrade: ", "Version found, Update required.")

                        if (responseJson.getBoolean("forceUpgrade")) {
                            Log.i("App Upgrade: ", "Force Update required.")
                            showForceUpgradePopup(parentActivity, responseJson.getString("message"))
                        } else {
                            Log.i(
                                "App Upgrade: ",
                                "Force Update is not required but update is recommended."
                            )
                            showUpgradePopup(parentActivity, responseJson.getString("message"))
                        }
                    } else {
                        Log.i("App Upgrade: ", "Version not found, Update not required.")
                    }
                } else {
                    Log.i("App Upgrade: ", "Request failed with http error code: ${response.code}")
                }
            }
        })
    }

    fun showForceUpgradePopup(parentActivity: Activity, updateMessage: String) {
        Log.i("App Upgrade: ", "Show force upgrade popup.")

        Thread {
            parentActivity.runOnUiThread {
                val builder = AlertDialog.Builder(parentActivity)
                builder.setMessage(updateMessage)
                builder.setCancelable(false)

                builder.setPositiveButton("Update Now", null)

                val alert = builder.create()

                alert.setOnShowListener {
                    val b: Button = alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                    b.setOnClickListener { onUserUpdate(parentActivity) }
                }

                alert.setTitle("Please Update")
                alert.show()
            }
        }.start()
    }

    fun showUpgradePopup(parentActivity: Activity, updateMessage: String) {
        Log.i("App Upgrade: ", "Show upgrade popup.")

        Thread {
            parentActivity.runOnUiThread {
                val builder = AlertDialog.Builder(parentActivity)
                builder.setMessage(updateMessage)
                builder.setCancelable(true)

                builder.setPositiveButton("Update Now",
                    DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                        onUserUpdate(parentActivity)
                        // If user click Later then dialog box is canceled.
                        dialog.cancel()
                    } as DialogInterface.OnClickListener)

                builder.setNegativeButton("Later",
                    DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                        onUserLater()
                        // If user click Later then dialog box is canceled.
                        dialog.cancel()
                    } as DialogInterface.OnClickListener)

                val alert = builder.create()
                alert.setTitle("Please Update")
                alert.show()
            }
        }.start()
    }

    fun onUserLater() {
        Log.i("App Upgrade: ", "Later.")
    }

    fun onUserUpdate(parentActivity: Activity) {
        Log.i("App Upgrade: ", "Update Now.")
        val appId = BuildConfig.APPLICATION_ID
        try {
            parentActivity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appId")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            parentActivity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appId")
                )
            )
        }
    }
}