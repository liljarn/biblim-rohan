package ru.liljvrn.biblimrohan.domain.models.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener::class)
data class ReportEntity(
    @Column(name = "report_title", updatable = false, nullable = false)
    val reportTitle: String = "",
) {
    @Id
    @Column(name = "report_id", columnDefinition = "UUID")
    @GeneratedValue
    var reportId: UUID? = null

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
}
