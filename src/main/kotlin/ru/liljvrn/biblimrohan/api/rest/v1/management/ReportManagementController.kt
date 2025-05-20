package ru.liljvrn.biblimrohan.api.rest.v1.management

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.liljvrn.biblimrohan.api.rest.json.response.DocumentLinkResponse
import ru.liljvrn.biblimrohan.api.rest.json.response.ReportPageResponse
import ru.liljvrn.biblimrohan.domain.services.ReportService
import ru.liljvrn.biblimrohan.support.mapper.toResponse
import ru.liljvrn.biblimrohan.support.reflection.ManagementApi
import java.util.*

@ManagementApi
@RestController
@RequestMapping("/api/v1/management/reports")
class ReportManagementController(
    private val reportService: ReportService
) {

    @GetMapping
    fun getReportsPage(@RequestParam page: Int): ReportPageResponse {
        return reportService.getReports(page).toResponse()
    }

    @GetMapping("/{reportId}")
    fun getReportLink(@PathVariable reportId: UUID): DocumentLinkResponse {
        return DocumentLinkResponse(reportService.getReportPresignedUrl(reportId))
    }
}
