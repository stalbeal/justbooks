package com.saba.core.network.models

import com.google.gson.annotations.SerializedName

data class APIIndustryIdentifierResponse(
    @SerializedName("type") val type: String,
    @SerializedName("identifier") val identifier: String
)