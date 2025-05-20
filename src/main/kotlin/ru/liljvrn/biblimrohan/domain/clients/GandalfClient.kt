package ru.liljvrn.biblimrohan.domain.clients

import ru.liljvrn.biblimrohan.domain.models.dto.UserData
import java.util.*

interface GandalfClient {

    fun getUserByToken(token: String): UserData

    fun getUserById(uuid: UUID): UserData
}
