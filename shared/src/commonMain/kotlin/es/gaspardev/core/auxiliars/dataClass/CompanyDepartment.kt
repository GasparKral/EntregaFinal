package es.gaspardev.core.auxiliars.dataClass

data class CompanyDepartment(val key: String) {
    /**
     * Enum of the company departments.
     */
    companion object {
        // Initial Departments
        private val IT = CompanyDepartment("IT")
        private val MARKETING = CompanyDepartment("MARKETING")
        private val SALES = CompanyDepartment("SALES")
        private val ADMIN = CompanyDepartment("ADMIN")
        private val HR = CompanyDepartment("HR")

        // Special department
        val NOT_DEFINED = CompanyDepartment("NOT_DEFINED")

        /**
         * All the departments
         */
        private val allDepartment: MutableSet<CompanyDepartment> = mutableSetOf(
            IT,
            MARKETING,
            SALES,
            ADMIN,
            HR
        )

        /**
         * Add a new department to the enum.
         *
         * @param department The department to be added.
         */
        fun addNewDepartment(department: CompanyDepartment) {
            allDepartment.add(department)
        }

        /**
         * Get a department by its key.
         *
         * @param key The key of the department.
         * @return The department.
         */
        fun getDepartment(key: String): CompanyDepartment {
            return allDepartment.first { it.key == key }
        }

    }

}