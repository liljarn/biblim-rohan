package ru.liljvrn.biblimrohan.api.rest.json.request

import org.springframework.web.multipart.MultipartFile

data class PdfBookRequest(
    val bookId: String,
    val book: MultipartFile
)
