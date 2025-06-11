package ru.liljvrn.biblimrohan.support.pdf

import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.EncryptionConstants
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.WriterProperties
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import ru.liljvrn.biblimrohan.domain.models.dto.BookReport
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object ReportPdfGenerator {

    fun generateBookAdditionReport(reportData: BookReport): ByteArray {
        val outputStream = ByteArrayOutputStream()

        val writerProperties = WriterProperties()
            .setStandardEncryption(
                null, null,
                EncryptionConstants.ALLOW_PRINTING or
                        EncryptionConstants.ALLOW_COPY or
                        EncryptionConstants.ALLOW_SCREENREADERS,
                EncryptionConstants.ENCRYPTION_AES_128
            )

        PdfWriter(outputStream, writerProperties).use { pdfWriter ->
            PdfDocument(pdfWriter).use { pdfDocument ->
                Document(pdfDocument).use { document ->
                    val font = PdfFontFactory.createFont(
                        "fonts/timesnewromanpsmt.ttf",
                        PdfEncodings.IDENTITY_H,
                        PdfFontFactory.EmbeddingStrategy.PREFER_NOT_EMBEDDED
                    )

                    document.setFont(font)

                    addContentToDocument(document, reportData)
                }
            }
        }

        return outputStream.toByteArray()
    }

    private fun addContentToDocument(document: Document, reportData: BookReport) {
        val title = Paragraph("Отчет о добавлении новой книги")
            .setFontSize(20f)
            .setTextAlignment(TextAlignment.CENTER)
            .setBold()
        document.add(title)

        val dateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"))
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        val dateTimeP = Paragraph("Дата и время создания отчета (московское время): $dateTime")
            .setFontSize(12f)
            .setTextAlignment(TextAlignment.RIGHT)
        document.add(dateTimeP)

        document.add(Paragraph("\n"))

        document.add(Paragraph("Информация о книге:").setFontSize(16f).setBold())
        document.add(Paragraph("Название: ${reportData.bookName}"))
        document.add(Paragraph("Автор: ${reportData.authorName}"))
        document.add(Paragraph("Год выпуска: ${reportData.releaseYear}"))
        document.add(Paragraph("Возрастное ограничение: ${reportData.ageLimit}"))
        document.add(Paragraph("Доступна для скачивания: ${if (reportData.downloadable) "Да" else "Нет"}"))

        val genresStr = reportData.genres.joinToString(", ")
        document.add(Paragraph("Жанры: $genresStr"))

        document.add(Paragraph("Описание:").setBold())
        document.add(Paragraph(reportData.description))

        document.add(Paragraph("\n"))

        document.add(Paragraph("Информация об экземплярах:").setFontSize(16f).setBold())
        document.add(Paragraph("Количество добавленных экземпляров: ${reportData.copiesIds.size}"))

        if (reportData.copiesIds.isNotEmpty()) {
            document.add(Paragraph("Идентификаторы экземпляров:").setBold())

            val table = Table(1)
            table.useAllAvailableWidth()

            reportData.copiesIds.forEach { uuid ->
                val cell = Cell().add(Paragraph(uuid.toString()))
                table.addCell(cell)
            }

            document.add(table)
        }

        document.add(Paragraph("\n"))
        document.add(Paragraph("Ответственный: ___________________"))
        document.add(Paragraph("Подпись библиотекаря: ___________________"))
    }
}
