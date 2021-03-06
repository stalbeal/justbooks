package com.saba.core.network.services

import com.saba.core.network.models.APIBookResponse
import com.saba.core.network.models.APIBooksResponse
import com.saba.core.network.models.APIItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("volumes")
    suspend fun getBooksByCategories(@Query("q") query: String, @Query("orderBy") orderBy: String) : APIBooksResponse

    @GET ("volumes/{id}")
    suspend fun getBookByIsbn(@Path ("id") isbn: String) : APIItemResponse

}