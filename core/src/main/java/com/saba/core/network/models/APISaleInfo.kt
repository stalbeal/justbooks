package com.saba.core.network.models

import com.google.gson.annotations.SerializedName

data class APISaleInfo(
    @SerializedName("country") val country: String,
    @SerializedName("saleability") val saleability: String,
    @SerializedName("isEbook") val isEbook: Boolean
)