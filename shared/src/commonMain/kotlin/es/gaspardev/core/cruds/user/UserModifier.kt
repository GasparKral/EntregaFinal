package es.gaspardev.core.cruds.user

import es.gaspardev.core.auxiliars.CareTaker
import es.gaspardev.core.auxiliars.dataClass.CompanyDepartment
import es.gaspardev.core.enums.UserRoles
import java.util.*

object UserModifier : CareTaker<User>() {

    private var instance: UserModifier? = null

    fun getInstance(): UserModifier {
        if (instance == null) {
            instance = UserModifier
        }
        return instance!!
    }

    fun modifyDepartment(user: User, newDepartment: CompanyDepartment) {
        modifyField(user, User::department, newDepartment)
        modifyLastModified(user)
    }

    fun modifyUserAccess(user: User, newUserAccess: UserRoles) {
        modifyField(user, User::userAccess, newUserAccess)
        modifyLastModified(user)
    }

    private fun modifyLastModified(user: User) {
        modifyField(user, User::lastModified, Date())
    }

}