package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.CareTaker
import es.gaspardev.core.cruds.comments.Comment
import es.gaspardev.core.enums.TaskStatus

object TaskModifier : CareTaker<Task>() {


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

    fun modifyStatus(task: Task, status: TaskStatus) {
        modifyField(task, Task::status, status)
        saveState(task)
    }
}