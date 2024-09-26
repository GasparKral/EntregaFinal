package es.gaspardev.core.cruds.user

import es.gaspardev.core.auxiliars.CRUD

object UserCrud : CRUD<User>() {

    private var instance: UserCrud? = null

    fun getInstance(): UserCrud {
        if (instance == null) {
            instance = UserCrud
        }
        return instance!!
    }

    override fun delete(value: User) {
        UserDeleter.delete(value)
        values.remove(value)
    }

    override fun modify() {
        val instance = UserModifier.getInstance()
        val oldUserIndex = values.indexOf(instance.getFirstState()!!)
        values[oldUserIndex] = instance.getState()!!
    }
}