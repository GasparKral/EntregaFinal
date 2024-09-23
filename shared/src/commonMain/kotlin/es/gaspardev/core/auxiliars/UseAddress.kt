package es.gaspardev.core.auxiliars

import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.enums.CompanyDepartment

sealed class UseAddress {
    data class  Department(val department: CompanyDepartment): UseAddress()
    data class  User(val user:es.gaspardev.core.cruds.user.User) : UseAddress()
}