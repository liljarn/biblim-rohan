package ru.liljvrn.biblimrohan.infrastructure.s3

import org.springframework.stereotype.Component
import ru.liljvrn.biblimrohan.domain.clients.PdfDocumentClient
import ru.liljvrn.biblimrohan.domain.models.types.FileType
import ru.liljvrn.biblimrohan.infrastructure.properties.S3Properties
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest
import java.io.InputStream
import java.time.Duration

private val EXPIRATION_TIME = Duration.ofHours(1)

@Component
class S3PdfDocumentClient(
    val s3Client: S3Client,
    val s3Presigner: S3Presigner,
    val properties: S3Properties,
) : PdfDocumentClient {

    override fun uploadDocument(file: InputStream, type: FileType, name: String) {
        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(properties.bucketName[type])
                .contentType("application/pdf")
                .key(name)
                .build(),
            RequestBody.fromBytes(file.readBytes())
        )
    }

    override fun getPresignedUrl(key: String, type: FileType): String {
        val getObjectRequest = GetObjectRequest.builder()
            .bucket(properties.bucketName[type])
            .key(key)
            .build()

        val presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(EXPIRATION_TIME)
            .getObjectRequest(getObjectRequest)
            .build()

        val presignedRequest = s3Presigner.presignGetObject(presignRequest)
        return presignedRequest.url().toString()
    }
}
