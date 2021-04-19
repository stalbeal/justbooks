package com.saba.core.models

import android.os.Parcelable
import com.saba.core.network.models.APIImageLinkResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageLink(
    val smallThumbnail: String?,
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val extraLarge: String?
) : Parcelable {
    constructor(apiImageLinkResponse: APIImageLinkResponse) : this(
        apiImageLinkResponse.smallThumbnail,
        apiImageLinkResponse.thumbnail,
        apiImageLinkResponse.small,
        apiImageLinkResponse.medium,
        apiImageLinkResponse.large,
        apiImageLinkResponse.extraLarge
    )
}