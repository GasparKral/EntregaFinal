package es.gaspardev.core.cruds.comments

import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.interfaces.Deleter
import kotlin.reflect.KProperty1

object CommentDeleter : Deleter<Comment> {
    private val deletedComments: ArrayList<Comment> = arrayListOf()

    fun delete(comment: Comment) {
        deletedComments.add(comment)
    }

    override fun undo(): Comment {
        return deletedComments.removeLast()
    }

    override fun undo(index: Int): Comment {
        return deletedComments.removeAt(index)
    }

    override fun clear() {
        deletedComments.clear()
    }

    fun searchByID(id: String): Comment {
        return deletedComments[getIndexOf(Comment::id, id)]
    }

    fun searchByUser(user: User): List<Comment> {
        return deletedComments.filter { it.user == user }
    }

    private fun <S> getIndexOf(searchBy: KProperty1<Comment, S>, searchValue: S): Int {
        return deletedComments.indexOf(deletedComments.find { it[searchBy] == searchValue })
    }


}