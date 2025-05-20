package ru.liljvrn.biblimrohan.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.liljvrn.biblimrohan.domain.models.entities.PdfBookEntity
import java.util.UUID

@Repository
interface PdfBookRepository : JpaRepository<PdfBookEntity, UUID> {
    fun findByBookId(bookId: Long): PdfBookEntity?
}
