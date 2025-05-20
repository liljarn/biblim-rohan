package ru.liljvrn.biblimrohan.domain.models.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

data class Report(
    val reportId: UUID,
    val reportTitle: String,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: LocalDateTime
)
