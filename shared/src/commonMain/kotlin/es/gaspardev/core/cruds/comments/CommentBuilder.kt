package es.gaspardev.core.cruds.comments

import es.gaspardev.core.interfaces.Builder

object CommentBuilder : Builder<Comment> {

    private var comment: Comment? = null

    override fun reset() {
        this.comment = null
    }

    override fun build(): Comment {
        val tempComment: Comment? = comment
        reset()
        return tempComment!!
    }

    override fun initialize(initialValue: Comment?) {
        this.comment = initialValue
    }
}