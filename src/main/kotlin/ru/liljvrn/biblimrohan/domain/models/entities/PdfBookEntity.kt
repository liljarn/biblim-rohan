package ru.liljvrn.biblimrohan.domain.models.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "pdf_books")
data class PdfBookEntity(
    @Column(name = "book_id", updatable = false, nullable = false, unique = true)
    val bookId: Long = 0,
) {
    @Id
    @Column(name = "pdf_book_id", columnDefinition = "UUID")
    @GeneratedValue
    var pdfBookId: UUID? = null

    val key: String
        get() = pdfBookId!!.toString() + ".pdf"
}
