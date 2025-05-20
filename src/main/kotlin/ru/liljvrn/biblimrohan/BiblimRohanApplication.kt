package ru.liljvrn.biblimrohan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import ru.liljvrn.biblimrohan.infrastructure.properties.S3Properties
import ru.liljvrn.biblimrohan.support.properties.InternalApiProperties
import ru.liljvrn.biblimrohan.support.properties.ManagementApiProperties

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableWebMvc
@EnableConfigurationProperties(S3Properties::class, ManagementApiProperties::class, InternalApiProperties::class)
class BiblimRohanApplication

fun main(args: Array<String>) {
    runApplication<BiblimRohanApplication>(*args)
}
