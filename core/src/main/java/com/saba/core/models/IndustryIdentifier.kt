package com.saba.justbooks.com.saba.justbooks.home.models

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