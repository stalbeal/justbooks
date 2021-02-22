package com.saba.core.models

import com.saba.core.network.models.APIIndustryIdentifierResponse

data class IndustryIdentifier(
    private val type: String,
    private val identifier: String
) {
    constructor(apiIndustryIdentifierResponse: APIIndustryIdentifierResponse) : this (
        apiIndustryIdentifierResponse.type,
        apiIndustryIdentifierResponse.identifier
    )
}