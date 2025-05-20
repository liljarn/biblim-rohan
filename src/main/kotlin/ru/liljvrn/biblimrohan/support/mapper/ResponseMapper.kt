package ru.liljvrn.biblimrohan.support.mapper

import org.springframework.data.domain.Page
import ru.liljvrn.biblimrohan.api.rest.json.response.ReportPageResponse
import ru.liljvrn.biblimrohan.domain.models.dto.Report

fun Page<Report>.toResponse(): ReportPageResponse = ReportPageResponse(
    total = totalElements,
    responses = content
)
