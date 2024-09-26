package es.gaspardev.utilities

import kotlin.uuid.ExperimentalUuidApi


object Utils {

    @OptIn(ExperimentalUuidApi::class)
    fun generateRandomId(): String {
        return kotlin.uuid.Uuid.random().toString()
    }


}