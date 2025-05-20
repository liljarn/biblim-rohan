package ru.liljvrn.biblimrohan.infrastructure.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.liljvrn.biblimrohan.infrastructure.properties.S3Properties
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import java.net.URI

@Configuration
class S3Configuration(
    private val properties: S3Properties
) {
    @Bean
    fun s3Client(): S3Client {

        return S3Client.builder()
            .credentialsProvider {
                AwsBasicCredentials.create(properties.credentials.accessKey, properties.credentials.secretKey)
            }
            .region(Region.of(properties.region))
            .endpointOverride(URI.create(properties.endpoint))
            .build()
    }

    @Bean
    fun s3Presigner(): S3Presigner {
        return S3Presigner.builder()
            .credentialsProvider {
                AwsBasicCredentials.create(
                    properties.credentials.accessKey,
                    properties.credentials.secretKey
                )
            }
            .region(Region.of(properties.region))
            .endpointOverride(URI.create(properties.endpoint))
            .build()
    }
}
