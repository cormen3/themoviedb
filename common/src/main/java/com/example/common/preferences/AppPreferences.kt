package com.example.common.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : PreferencesHelper {
    override var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN, null)
        set(value) = sharedPreferences.edit { putString(ACCESS_TOKEN, value) }

    override var imagesBaseUrl: String?
        get() = sharedPreferences.getString(IMAGES_BASE_URL, null)
        set(value) = sharedPreferences.edit { putString(IMAGES_BASE_URL, value) }

    override fun removeAccessToken() {
        sharedPreferences.edit { remove(ACCESS_TOKEN) }
    }

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val IMAGES_BASE_URL = "IMAGES_BASE_URL"
    }
}