package com.paweljablonski.quote.repository

import com.paweljablonski.quote.exception.DataOrException
import com.paweljablonski.quote.model.Quote
import com.paweljablonski.quote.network.QuoteApi
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteApi
    ){

    private val quote =
        DataOrException<Quote, Boolean, Exception>()

    suspend fun getQuote(): DataOrException<Quote, Boolean, Exception>{
        try {
            quote.loading = true
            quote.data = api.getQuote()
            if (quote.data.toString().isNotEmpty()) quote.loading = false

        }catch (exception: Exception){
            quote.e = exception
        }
        return quote
    }

}