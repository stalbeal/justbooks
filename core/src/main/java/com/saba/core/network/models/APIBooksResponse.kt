package com.saba.core.network.models

import com.google.gson.annotations.SerializedName

data class APIBooksResponse(
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<APIItemResponse>?
)