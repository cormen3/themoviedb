package com.example.common.preferences

interface PreferencesHelper {
    var accessToken: String?
    var imagesBaseUrl: String?
    fun removeAccessToken()
}


