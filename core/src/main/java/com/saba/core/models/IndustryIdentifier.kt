package com.saba.core.models

import android.os.Parcelable
import com.saba.core.network.models.APIIndustryIdentifierResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IndustryIdentifier(
    private val type: String,
    private val identifier: String
) : Parcelable {
    constructor(apiIndustryIdentifierResponse: APIIndustryIdentifierResponse) : this (
        apiIndustryIdentifierResponse.type,
        apiIndustryIdentifierResponse.identifier
    )
}