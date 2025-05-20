package ru.liljvrn.biblimrohan.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import ru.liljvrn.biblimrohan.domain.models.types.FileType

@ConfigurationProperties(prefix = "cloud.aws")
class S3Properties {
    lateinit var credentials: Credentials
    lateinit var region: String
    lateinit var endpoint: String
    lateinit var bucketName: Map<FileType, String>

    data class Credentials(
        val accessKey: String,
        val secretKey: String
    )
}
