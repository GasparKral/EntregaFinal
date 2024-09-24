package es.gaspardev.core.cruds.comments

import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.interfaces.Copyable
import es.gaspardev.utilities.Utils
import java.util.Date
import kotlin.reflect.KProperty1

data class Comment(val id: String = Utils.generateRandomId(), val user: User, var text: String, val date: Date) :
    Copyable {
    override fun copy(): Comment {
        return copy()
    }

    operator fun <S> get(searchBy: KProperty1<Comment, S>): S {
        return searchBy.get(this)
    }
}