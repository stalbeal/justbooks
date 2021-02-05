package com.saba.core.network.services

import com.saba.core.network.models.APIBooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("volumes")
    suspend fun getBooksByCategories(@Query("q") query: String, @Query("orderBy") orderBy: String) : APIBooksResponse

}