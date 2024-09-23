package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.CareTaker
import es.gaspardev.core.cruds.Comments.Comment

class TaskCareTaker : CareTaker<Task>() {

    fun addNewTag(task: Task, tag: String) {
        task.tags.add(tag)
        saveState(task)
    }

    fun deleteTag(task: Task, tag: String) {
        task.tags.remove(tag)
        saveState(task)
    }

    fun addNewComment(task: Task, comment: Comment) {
        task.comments.add(comment)
        saveState(task)
    }

    fun deleteComment(task: Task, comment: Comment) {
        task.comments.remove(comment)
        saveState(task)
    }
}