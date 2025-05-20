package ru.liljvrn.biblimrohan.api.rest.v1.internal

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.liljvrn.biblimrohan.api.rest.json.request.BookReportRequest
import ru.liljvrn.biblimrohan.domain.services.ReportService
import ru.liljvrn.biblimrohan.support.consts.BOOKER
import ru.liljvrn.biblimrohan.support.mapper.toDto
import ru.liljvrn.biblimrohan.support.reflection.InternalApi

@RestController
@RequestMapping("/api/v1/internal/reports")
class ReportInternalController(
    private val reportService: ReportService,
) {
    @PostMapping
    @InternalApi(BOOKER)
    fun addNewReport(@RequestBody request: BookReportRequest) {
        reportService.addNewReport(request.toDto())
    }
}
