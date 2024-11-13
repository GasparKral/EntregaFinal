package es.gaspardev.controllers

import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.auxiliars.dataClass.CompanyDepartment
import es.gaspardev.core.enums.UserRoles

object LoggedUser {

    var user: User? = User(
        name = "Gaspar",
        email = "kqG4w@example.com",
        userAccess = UserRoles.ADMIN,
        department = CompanyDepartment.getDepartment("IT")
    )


}