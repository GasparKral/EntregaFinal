package es.gaspardev.core.enums

enum class SortBy {
    PRIORITY,
    DUE_DATE,
    CREATION_DATE,
    TASK_STATUS;

    companion object {

        private val translations: Map<String, SortBy> = mapOf(
            "Creation Date" to CREATION_DATE,
            "Data di creazione" to CREATION_DATE,
            "Date de création" to CREATION_DATE,
            "Due Date" to DUE_DATE,
            "Data di scadenza" to DUE_DATE,
            "Date d'expiration" to DUE_DATE,
            "Fecha de creación" to CREATION_DATE,
            "Fecha de vencimiento" to DUE_DATE,
            "Prioridad" to PRIORITY,
            "Priorité" to PRIORITY,
            "Priorità" to PRIORITY,
            "Priority" to PRIORITY,
            "Statut" to TASK_STATUS,
            "Status" to TASK_STATUS,
            "Stato" to TASK_STATUS,
            "Estado" to TASK_STATUS,
        )

        fun fromString(value: String): SortBy {
            return translations[value] ?: PRIORITY
        }

    }

}