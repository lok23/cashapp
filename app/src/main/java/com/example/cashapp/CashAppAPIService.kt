package com.example.cashapp

import com.example.cashapp.stocks.Stocks
import retrofit2.http.GET

interface CashAppAPIService {

    @GET("/cash-homework/cash-stocks-api/portfolio.json")
    suspend fun getValidCashAppData(): Stocks

    @GET("/cash-homework/cash-stocks-api/portfolio_malformed.json")
    suspend fun getMalformedCashAppData(): Stocks

    @GET("/cash-homework/cash-stocks-api/portfolio_empty.json")
    suspend fun getEmptyCashAppData(): Stocks
}