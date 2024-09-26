package es.gaspardev.core.cruds.comments

import es.gaspardev.core.auxiliars.CareTaker
import java.util.*

object CommentModifier : CareTaker<Comment>() {

    private var instance: CommentModifier? = null

    fun getInstance(): CommentModifier {
        if (instance == null) {
            instance = CommentModifier
        }
        return instance!!
    }

    private fun modifyDate(comment: Comment) {
        modifyField(comment, Comment::date, Date())
    }

    fun modifyText(comment: Comment, text: String) {
        modifyField(comment, Comment::text, text)
        modifyDate(comment)
    }

}