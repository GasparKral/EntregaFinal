package es.gaspardev.core.auxiliars.seals

import es.gaspardev.core.auxiliars.dataClass.CompanyDepartment

sealed class UseAddress {
    data class  Department(val department: CompanyDepartment): UseAddress()
    data class  User(val user:es.gaspardev.core.cruds.user.User) : UseAddress()
}