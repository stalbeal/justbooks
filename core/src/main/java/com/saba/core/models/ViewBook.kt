package com.saba.core.models

class ViewBook(
    val industryIdentifier: List<IndustryIdentifier>,
    val title: String,
    val subtitle: String?,
    val authors: List<String>,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val imageLinks: ImageLink?,
    val description: String?,
    val publisher: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val mainCategory: String?,
    val categories: List<String>?,
    val contentVersion: String?,
    val language: String,
    val googleBooksId: String
) {
    constructor(book: Book) : this(
        book.industryIdentifiers,
        book.title,
        book.subtitle,
        book.authors!!,
        book.averageRating,
        book.ratingsCount,
        book.imageLinks,
        book.description,
        book.publisher,
        book.publishedDate,
        book.pageCount,
        book.mainCategory,
        book.categories,
        book.contentVersion,
        book.language,
        book.googleBooksId
    )
}

