package es.gaspardev.core.cruds.user


import es.gaspardev.core.enums.CompanyDepartment
import es.gaspardev.core.interfaces.Deleter
import kotlin.reflect.KProperty1

object UserDeleter : Deleter<User> {

    private val deletedUsers: ArrayList<User> = arrayListOf()

    override fun delete(value: User) {
        deletedUsers.add(value)
    }

    override fun undo(): User {
        return deletedUsers.removeLast()
    }

    override fun undo(index: Int): User {
        return deletedUsers.removeAt(index)
    }

    override fun clear() {
        deletedUsers.clear()
    }

    fun searchByID(id: String): User {
        return deletedUsers[getIndexOf(User::id, id)]
    }

    fun searchByName(userName: String): User {
        return deletedUsers[getIndexOf(User::name, userName)]
    }

    fun searchByDepartment(department: CompanyDepartment): List<User> {
        return deletedUsers.filter { it.department == department }
    }

    private fun <S> getIndexOf(searchBy: KProperty1<User, S>, searchValue: S): Int {
        return deletedUsers.indexOf(deletedUsers.find { searchBy.get(it) == searchValue })
    }
}