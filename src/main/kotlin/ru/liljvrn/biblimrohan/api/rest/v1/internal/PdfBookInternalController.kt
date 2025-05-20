package ru.liljvrn.biblimrohan.api.rest.v1.internal

import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.liljvrn.biblimrohan.api.rest.json.request.PdfBookRequest
import ru.liljvrn.biblimrohan.domain.services.PdfBookService
import ru.liljvrn.biblimrohan.support.consts.BOOKER
import ru.liljvrn.biblimrohan.support.mapper.toDto
import ru.liljvrn.biblimrohan.support.reflection.InternalApi

@RestController
@RequestMapping("/api/v1/internal/pdfbook")
class PdfBookInternalController(
    private val pdfBookService: PdfBookService
) {

    @PostMapping
    @InternalApi(BOOKER)
    fun addNewBook(@ModelAttribute request: PdfBookRequest) {
        pdfBookService.addNewPdfBook(request.toDto())
    }
}
