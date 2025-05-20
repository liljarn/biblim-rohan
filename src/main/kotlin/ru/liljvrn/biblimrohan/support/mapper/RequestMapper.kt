package ru.liljvrn.biblimrohan.support.mapper

import ru.liljvrn.biblimrohan.api.rest.json.request.BookReportRequest
import ru.liljvrn.biblimrohan.api.rest.json.request.PdfBookRequest
import ru.liljvrn.biblimrohan.domain.models.dto.BookReport
import ru.liljvrn.biblimrohan.domain.models.dto.PdfBook

fun BookReportRequest.toDto(): BookReport = BookReport(
    bookName = bookName,
    authorName = authorName,
    releaseYear = releaseYear,
    description = description,
    ageLimit = ageLimit,
    downloadable = downloadable,
    genres = genres,
    copiesIds = copiesIds,
)

fun PdfBookRequest.toDto(): PdfBook = PdfBook(
    bookId = bookId.toLong(),
    book = book.inputStream
)
