package com.paweljablonski.quote.network

import com.paweljablonski.quote.model.Quote
import com.paweljablonski.quote.utils.Constants
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuoteApi {

    @GET(value = Constants.API_KEY)
    suspend fun getQuote(): Quote
}