package ru.liljvrn.biblimrohan.api.rest.v1.client

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.liljvrn.biblimrohan.api.rest.json.response.DocumentLinkResponse
import ru.liljvrn.biblimrohan.domain.services.AuthService
import ru.liljvrn.biblimrohan.domain.services.PdfBookService

@RestController
@RequestMapping("/api/v1/pdfbook")
class PdfBookController(
    private val authService: AuthService,
    private val pdfBookService: PdfBookService
) {

    @GetMapping("/{bookId}")
    fun getBookUrl(
        @PathVariable bookId: Long,
        @RequestHeader("Authorization") token: String?
    ): DocumentLinkResponse {
        authService.checkAuthorization(token)
        return DocumentLinkResponse(pdfBookService.getBookPresignedUrl(bookId))
    }
}
