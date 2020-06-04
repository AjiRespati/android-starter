package com.utek.android.utekapp.network

import com.squareup.moshi.Json

data class AppListProperty (
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
)