package com.saba.justbooks.com.saba.justbooks.home.models

import com.saba.core.network.models.APIImageLinkResponse

data class ImageLink(
    val smallThumbnail: String?,
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val extraLarge: String?
) {
    constructor(apiImageLinkResponse: APIImageLinkResponse) : this(
        apiImageLinkResponse.smallThumbnail,
        apiImageLinkResponse.thumbnail,
        apiImageLinkResponse.small,
        apiImageLinkResponse.medium,
        apiImageLinkResponse.large,
        apiImageLinkResponse.extraLarge
    )
}