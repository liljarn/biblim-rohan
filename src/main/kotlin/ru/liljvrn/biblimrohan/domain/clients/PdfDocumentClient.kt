package ru.liljvrn.biblimrohan.domain.clients

import ru.liljvrn.biblimrohan.domain.models.types.FileType
import java.io.InputStream

interface PdfDocumentClient {

    fun uploadDocument(file: InputStream, type: FileType, name: String)

    fun getPresignedUrl(key: String, type: FileType): String
}
