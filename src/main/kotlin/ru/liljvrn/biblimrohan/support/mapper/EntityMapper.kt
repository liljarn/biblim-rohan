package ru.liljvrn.biblimrohan.support.mapper

import ru.liljvrn.biblimrohan.domain.models.dto.Report
import ru.liljvrn.biblimrohan.domain.models.entities.ReportEntity

fun ReportEntity.toDto(): Report = Report(
    reportId = reportId!!,
    reportTitle = reportTitle,
    createdAt = createdAt!!
)
