package com.saba.core.models

import com.saba.core.network.models.APIItemResponse

data class Book(
    val industryIdentifiers: List<IndustryIdentifier>?,
    val title: String,
    val subtitle: String?,
    val authors: List<String>?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val imageLinks: ImageLink?,
    val description: String?,
    val publisher: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val mainCategory: String?,
    val categories: List<String>? = listOf(),
    val contentVersion: String?,
    val language: String?,
    val googleBooksId: String
) {
    constructor(apiItemResponse: APIItemResponse) : this(
        apiItemResponse.bookInfo.industryIdentifier?.map {
            IndustryIdentifier(it)
        },
        apiItemResponse.bookInfo.title,
        apiItemResponse.bookInfo.subtitle,
        apiItemResponse.bookInfo.authors,
        apiItemResponse.bookInfo.averageRating,
        apiItemResponse.bookInfo.ratingsCount,
        apiItemResponse.bookInfo.imageLinks?.let { ImageLink(it) },
        apiItemResponse.bookInfo.description,
        apiItemResponse.bookInfo.publisher,
        apiItemResponse.bookInfo.publishedDate,
        apiItemResponse.bookInfo.pageCount,
        apiItemResponse.bookInfo.mainCategory,
        apiItemResponse.bookInfo.categories,
        apiItemResponse.bookInfo.contentVersion,
        apiItemResponse.bookInfo.language,
        apiItemResponse.id
    )
}
