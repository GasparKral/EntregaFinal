package es.gaspardev.core.enums

enum class TaskPriority {
    NOT_DEFINED,
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL;

    companion object {

        private val translations: Map<String, TaskPriority> = mapOf(
            "Non définié" to NOT_DEFINED,
            "Non definito" to NOT_DEFINED,
            "Nicht definiert" to NOT_DEFINED,
            "Not Defined" to NOT_DEFINED,
            "Basse" to LOW,
            "Bajo" to LOW,
            "Basso" to LOW,
            "Low" to LOW,
            "Mediane" to MEDIUM,
            "Medio" to MEDIUM,
            "Mittel" to MEDIUM,
            "Medium" to MEDIUM,
            "Haute" to HIGH,
            "Alto" to HIGH,
            "Hoch" to HIGH,
            "High" to HIGH,
            "Critique" to CRITICAL,
            "Crítica" to CRITICAL,
            "Critico" to CRITICAL,
            "Critical" to CRITICAL,
            "Kritisch" to CRITICAL
        )

        fun fromString(value: String): TaskPriority {
            return translations[value] ?: NOT_DEFINED
        }
    }
}