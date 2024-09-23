package es.gaspardev.core.cruds.user

import es.gaspardev.core.enums.CompanyDepartment
import es.gaspardev.core.enums.UserRoles
import es.gaspardev.core.enums.UserStatus
import es.gaspardev.utilities.Utils
import java.util.Date

data class User (

    val id: String = Utils.generateRandomId(),
    var name: String,
    var email: String,
    var department: CompanyDepartment? = null,
    var status: UserStatus = UserStatus.UNKNOWN,
    var userAccess: UserRoles,
    val created: Date = Date(),
    var lastModified: Date = Date()
)