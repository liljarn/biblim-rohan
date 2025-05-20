package ru.liljvrn.biblimrohan.domain.services

import org.springframework.stereotype.Service
import ru.liljvrn.biblimrohan.domain.clients.PdfDocumentClient
import ru.liljvrn.biblimrohan.domain.models.dto.PdfBook
import ru.liljvrn.biblimrohan.domain.models.entities.PdfBookEntity
import ru.liljvrn.biblimrohan.domain.models.types.FileType
import ru.liljvrn.biblimrohan.domain.repositories.PdfBookRepository

@Service
class PdfBookService(
    private val pdfDocumentClient: PdfDocumentClient,
    private val pdfBookRepository: PdfBookRepository
) {

    fun addNewPdfBook(pdfBook: PdfBook) {
        val pdf = pdfBookRepository.save(PdfBookEntity(pdfBook.bookId))
        pdfDocumentClient.uploadDocument(pdfBook.book, FileType.BOOK, pdf.key)
    }

    fun getBookPresignedUrl(bookId: Long): String {
        val pdf = checkNotNull(pdfBookRepository.findByBookId(bookId))
        return pdfDocumentClient.getPresignedUrl(pdf.key, FileType.BOOK)
    }
}
