package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.CareTaker
import es.gaspardev.core.cruds.comments.Comment

object TaskModifier : CareTaker<Task>() {

    private var instance: TaskModifier? = null

    fun getInstance(): TaskModifier {
        if (instance == null) {
            instance = TaskModifier
        }
        return instance!!
    }

    fun addNewTag(task: Task, tag: String) {
        modifyFieldList(task, Task::tags, tag)
        saveState(task)
    }

    fun deleteTag(task: Task, tag: String) {
        modifyFieldList(task, Task::tags, tag)
        saveState(task)
    }

    fun addNewComment(task: Task, comment: Comment) {
        modifyFieldList(task, Task::comments, comment)
        saveState(task)
    }

    fun deleteComment(task: Task, comment: Comment) {
        modifyFieldList(task, Task::comments, comment)
        saveState(task)
    }
}