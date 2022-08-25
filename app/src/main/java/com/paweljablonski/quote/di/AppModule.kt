package com.paweljablonski.quote.di

import com.paweljablonski.quote.network.QuoteApi
import com.paweljablonski.quote.repository.QuoteRepository
import com.paweljablonski.quote.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideQuoteRepository(api: QuoteApi) = QuoteRepository(api)

    @Singleton
    @Provides
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

}