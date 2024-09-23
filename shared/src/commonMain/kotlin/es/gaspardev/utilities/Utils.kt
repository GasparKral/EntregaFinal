package es.gaspardev.utilities

import kotlin.uuid.ExperimentalUuidApi


class Utils {

    companion object{
        @OptIn(ExperimentalUuidApi::class)
        fun generateRandomId(): String {
            return kotlin.uuid.Uuid.random().toString()
        }
    }

}