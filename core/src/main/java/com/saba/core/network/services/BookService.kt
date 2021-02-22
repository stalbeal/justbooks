package com.saba.core.network.services

import com.saba.core.network.models.APIBookResponse
import com.saba.core.network.models.APIBooksResponse
import com.saba.core.network.models.APIItemResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface BookService {

    @GET("volumes")
    suspend fun getBooksByCategories(@Query("q") query: String, @Query("orderBy") orderBy: String) : APIBooksResponse

    @GET ("volumes")
    suspend fun getBookByIsbn(isbn: String) : APIItemResponse

}