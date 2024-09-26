package es.gaspardev.core.cruds.comments

import es.gaspardev.core.auxiliars.CRUD

object CommentCrud : CRUD<Comment>() {

    private var instance: CommentCrud? = null

    fun getInstance(): CommentCrud {
        if (instance == null) {
            instance = CommentCrud
        }
        return instance!!
    }

    override fun delete(value: Comment) {
        CommentDeleter.delete(value)
        values.remove(value)
    }

    override fun modify() {
        val instance: CommentModifier = CommentModifier.getInstance()
        val oldCommentIndex = values.indexOf(instance.getFirstState()!!)
        values[oldCommentIndex] = instance.getState()!!
    }
}