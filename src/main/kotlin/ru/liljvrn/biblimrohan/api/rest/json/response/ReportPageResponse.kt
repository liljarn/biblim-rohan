package ru.liljvrn.biblimrohan.api.rest.json.response

import ru.liljvrn.biblimrohan.domain.models.dto.Report

data class ReportPageResponse(
    val total: Long,
    val responses: List<Report>
)
