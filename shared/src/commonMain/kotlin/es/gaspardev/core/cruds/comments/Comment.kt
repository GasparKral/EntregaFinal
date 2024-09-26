package es.gaspardev.core.cruds.comments

import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.interfaces.Copyable
import es.gaspardev.utilities.Utils
import java.util.Date
import kotlin.reflect.KProperty0


data class Comment(val id: String = Utils.generateRandomId(), val user: User, var text: String, var date: Date) :
    Copyable {
    override fun copy(): Comment {
        return copy()
    }

    operator fun <T> get(searchBy: KProperty0<T>): T {
        return searchBy.get()
    }
}