package ru.liljvrn.biblimrohan.domain.services

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import ru.liljvrn.biblimrohan.domain.clients.PdfDocumentClient
import ru.liljvrn.biblimrohan.domain.models.dto.BookReport
import ru.liljvrn.biblimrohan.domain.models.dto.Report
import ru.liljvrn.biblimrohan.domain.models.entities.ReportEntity
import ru.liljvrn.biblimrohan.domain.models.types.FileType
import ru.liljvrn.biblimrohan.domain.repositories.ReportRepository
import ru.liljvrn.biblimrohan.support.mapper.toDto
import ru.liljvrn.biblimrohan.support.pageRequest
import ru.liljvrn.biblimrohan.support.pdf.ReportPdfGenerator
import java.util.*

@Service
class ReportService(
    private val reportRepository: ReportRepository,
    private val pdfDocumentClient: PdfDocumentClient
) {

    fun addNewReport(bookReport: BookReport) {
        val report = reportRepository.save(ReportEntity(bookReport.bookName))
        val pdf = ReportPdfGenerator.generateBookAdditionReport(bookReport)

        pdfDocumentClient.uploadDocument(pdf.inputStream(), FileType.REPORT, report.reportId.toString())
    }

    fun getReportPresignedUrl(reportId: UUID): String =
        pdfDocumentClient.getPresignedUrl(reportId.toString(), FileType.REPORT)

    fun getReports(page: Int): Page<Report> = pageRequest(page) {
        reportRepository.findAll(it).map { report -> report.toDto() }
    }
}
