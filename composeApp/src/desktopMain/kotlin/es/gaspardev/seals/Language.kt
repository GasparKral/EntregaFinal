package es.gaspardev.seals

sealed class Language(val isoFormat: String) {
    data object EN : Language("en-GB")
    data object ES : Language("es-ES")
    data object IT : Language("it-IT")
    data object FR : Language("fr-FR")
    data object DE : Language("de-DE")
}