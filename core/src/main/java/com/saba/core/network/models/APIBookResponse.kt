package com.saba.core.network.models

import com.google.gson.annotations.SerializedName

data class APIBookResponse(
    @SerializedName("industryIdentifiers") val industryIdentifier: List<APIIndustryIdentifierResponse>?,
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String>? = listOf(),
    @SerializedName("printType") val printType: String,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("averageRating") val averageRating: Double?,
    @SerializedName("ratingsCount") val ratingsCount: Int?,
    @SerializedName("imageLinks") val imageLinks: APIImageLinkResponse?,
    @SerializedName("description") val description: String?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @SerializedName("pageCount") val pageCount: Int?,
    @SerializedName("mainCategory") val mainCategory: String?,
    @SerializedName("categories") val categories: List<String>? = listOf(),
    @SerializedName("contentVersion") val contentVersion: String?,
    @SerializedName("language") val language: String
)