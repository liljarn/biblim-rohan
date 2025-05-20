package ru.liljvrn.biblimrohan.domain.models.dto

import java.io.InputStream

data class PdfBook(
    val bookId: Long,
    val book: InputStream
)
