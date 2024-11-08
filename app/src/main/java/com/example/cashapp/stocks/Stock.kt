package com.example.cashapp.stocks

data class Stock(
    val currency: String,
    val current_price_cents: Int,
    val current_price_timestamp: Int,
    val name: String,
    val quantity: Int?,
    val ticker: String
)