package es.gaspardev.core.cruds.user

import es.gaspardev.core.enums.CompanyDepartment
import es.gaspardev.core.enums.UserRoles
import es.gaspardev.core.enums.UserStatus
import es.gaspardev.core.interfaces.Copyable
import es.gaspardev.utilities.Utils
import java.util.Date
import kotlin.reflect.KProperty0

data class User(

    val id: String = Utils.generateRandomId(),
    var name: String,
    var email: String,
    var department: CompanyDepartment? = null,
    var status: UserStatus = UserStatus.UNKNOWN,
    var userAccess: UserRoles,
    val created: Date = Date(),
    var lastModified: Date = Date()
) : Copyable {

    override fun copy(): User {
        return User(
            id = this.id,
            name = this.name,
            email = this.email,
            department = this.department,
            status = this.status,
            userAccess = this.userAccess,
            created = this.created,
            lastModified = Date()
        )
    }


    operator fun <T> get(searchBy: KProperty0<T>): T {
        return searchBy.get()
    }
}