# App Upgrade: Android Kotlin Demo App

[App Upgrade: Android Kotlin Demo App](https://github.com/appupgrade-dev/app_upgrade_android_kotlin_demo_app) is a sample android app written in Kotlin integrated with [App Upgrade Android SDK](https://github.com/appupgrade-dev/app-upgrade-android-sdk) to demonstrate how Force upgrade works in android app with [App Upgrade](https://appupgrade.dev).

You can find the android kotlin demo app integrated via API in [integrate-via-api](https://github.com/appupgrade-dev/app_upgrade_android_kotlin_demo_app/tree/integrate-via-api) branch.

You can find Java version [here](https://github.com/appupgrade-dev/app_upgrade_android_java_demo_app)

## Installation

To get started, clone the repo:

`$ git clone https://github.com/appupgrade-dev/app_upgrade_android_kotlin_demo_app`

## Setup

1. Open the directory in Andriod Studio.

2. Open the `app_upgrade_android_kotlin_demo_app\app\src\main\java\com\appupgrade\app_upgrade_android_kotlin_demo_app\MainActivity.kt` file, and replace required values in line 16-23
   | Key                   | Value Description |
   | -----------------------|-------------|
   | `xApiKey`     | Your API Key. Required. |
   | `appName`  | Your app name. Required. |
   | `appVersion`  | Your app version. Required. |
   | `platform`  | Your app platform, ex: android or iOS. Required. |
   | `environment`  | Your app environment, ex: dev, stating, production. Required. |
   | `appLanguage`  | Your app language code, ex: en, es. Optional. |
   | `preferredAndroidMarket`  | Your preferred android market, ex: amazon, huawei or other. Optional. |
   | `otherAndroidMarketUrl`  | Other market place url from where update can be done. Optional. Required only if PreferredAndroidMarket is other |

If you want users to redirect to store other than app store or playstore. You can add these additional parameters in appInfo

- preferredAndroidMarket: PreferredAndroidMarket.HUAWEI // or PreferredAndroidMarket.AMAZON or PreferredAndroidMarket.OTHER If not provided default is Google playstore.
  If you want to redirect user to some other android market place you can add the following fields:

- preferredAndroidMarket: PreferredAndroidMarket.OTHER
- otherAndroidMarketUrl: 'https://someotherandroidmarket.com/app/id'// Required if preferredAndroidMarket is other.   

2. Save `MainActivity.kt` file.

3. Run the app.

## Usage:   

1. Head to [App Upgrade](https://appupgrade.dev) and signup if not already.
2. Create a new Project.
3. Grab the API Key. You will need to provide the same in the required fields mentioned in above section.
4. Create a new version with the required details and force upgrade field as selected. Make sure the details provided in version matches with the details provided in the App.js file.
5. Refresh the app and you will be able to see a popup for force upgrade.
6. Edit the version in dashboard with Force upgrade field unselected. Refresh the app and you will see a popup with soft upgrade with a button to upgrade later.
 > For full details on how to use App Upgrade dashboard read our [Documentation](https://appupgrade.dev/docs)

## Troubleshoot
If you are using `minifyEnabled true` it may obfuscate the code. Add the following rule in proguard-rules.pro to make sure SDK shows the popup.
```
# Keep all classes in the App Upgrade SDK package
-keep class com.appupgrade.app_upgrade_android_sdk.** { *; }

```

## Screenshots
 ![forceupgrade_android_kotlin](https://raw.githubusercontent.com/appupgrade-dev/app-upgrade-assets/main/images/forceupgrade_android_kotlin.png)

## Need help?

If you're looking for help, try our [Documentation](https://appupgrade.dev/docs/) or our [FAQ](https://appupgrade.dev/docs/app-upgrade-faq).
If you need support please write to us at support@appupgrade.dev
