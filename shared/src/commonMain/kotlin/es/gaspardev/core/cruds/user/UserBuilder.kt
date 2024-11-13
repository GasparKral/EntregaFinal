package es.gaspardev.core.cruds.user

import es.gaspardev.core.auxiliars.dataClass.CompanyDepartment
import es.gaspardev.core.enums.UserRoles
import es.gaspardev.core.interfaces.Builder

object UserBuilder : Builder<User> {

    private var user: User? = null

    override fun reset() {
        this.user = null
    }

    override fun build(): User {
        val tempUser: User? = user
        reset()
        return tempUser!!
    }

    override fun initialize(initialValue: User?) {
        this.user = initialValue
    }

    fun newUser(name: String, email: String, userAccess: UserRoles) {
        this.user = User(
            name = name,
            email = email,
            userAccess = userAccess
        )
    }

    fun setDepartment(department: CompanyDepartment) {
        this.user!!.department = department
    }
}