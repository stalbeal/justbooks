package com.saba.core.network.models

import com.google.gson.annotations.SerializedName

data class APIItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("selfLink") val selfLink: String,
    @SerializedName("volumeInfo") val bookInfo: APIBookResponse,
    @SerializedName("saleInfo") val saleInfo: APISaleInfo
)

