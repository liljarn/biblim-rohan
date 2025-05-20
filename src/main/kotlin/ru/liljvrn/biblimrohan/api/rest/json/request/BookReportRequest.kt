package ru.liljvrn.biblimrohan.api.rest.json.request

import java.util.*

data class BookReportRequest(
    val bookName: String,
    val authorName: String,
    val releaseYear: Short,
    val ageLimit: Short,
    val description: String,
    val downloadable: Boolean,
    val genres: List<String>,
    val copiesIds: List<UUID>
)
