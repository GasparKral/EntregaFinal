package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.CareTaker
import es.gaspardev.core.cruds.comments.Comment

object TaskModifier : CareTaker<Task>() {

    private var intance: TaskModifier? = null

    fun getInstance(): TaskModifier {
        if (intance == null) {
            intance = TaskModifier
        }
        return intance!!
    }

    fun addNewTag(task: Task, tag: String) {
        modifyField(task, Task::tags, tag)
        saveState(task)
    }

    fun deleteTag(task: Task, tag: String) {
        modifyField(task, Task::tags, tag)
        saveState(task)
    }

    fun addNewComment(task: Task, comment: Comment) {
        modifyField(task, Task::comments, comment)
        saveState(task)
    }

    fun deleteComment(task: Task, comment: Comment) {
        modifyField(task, Task::comments, comment)
        saveState(task)
    }
}