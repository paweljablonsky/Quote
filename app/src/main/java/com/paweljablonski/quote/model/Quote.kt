package com.paweljablonski.quote.model

data class Quote(
    var content: String,
    val id: Int,
    val language_code: String,
    val originator: Originator,
    val tags: List<String>,
    val url: String
)