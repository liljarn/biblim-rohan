package ru.liljvrn.biblimrohan.support.reflection

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class InternalApi(vararg val service: String)
