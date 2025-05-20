package ru.liljvrn.biblimrohan.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.liljvrn.biblimrohan.domain.models.entities.ReportEntity
import java.util.UUID

@Repository
interface ReportRepository : JpaRepository<ReportEntity, UUID>
