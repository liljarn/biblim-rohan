package ru.liljvrn.biblimrohan.domain.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.liljvrn.biblimrohan.domain.clients.GandalfClient

@Service
class AuthService(
    private val gandalfClient: GandalfClient
) {

    fun checkAuthorization(token: String?) {
        token?.replace("Bearer ", "")?.runCatching {
            gandalfClient.getUserByToken(this)
        }?.onFailure {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token")
        } ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials")
    }
}
