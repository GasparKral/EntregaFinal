package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.UseAddress
import es.gaspardev.core.cruds.Comments.Comment
import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.enums.TaskPriority
import es.gaspardev.core.enums.TaskStatus
import es.gaspardev.core.interfaces.Copyable
import es.gaspardev.utilities.Utils
import java.util.Date

data class Task(
    val id: String = Utils.generateRandomId(),
    val userCreator: User,
    var addressTo: UseAddress?,
    var addressFrom: UseAddress?,
    val creationDate: Date = Date(),
    var title: String?,
    var description: String?,
    var message: String?,
    var dueDate: Date?,
    var priority: TaskPriority,
    var status: TaskStatus,
    var canGuestsSee: Boolean = false,
    val tags: ArrayList<String> = ArrayList(),
    val comments: ArrayList<Comment> = ArrayList()
) : Copyable {

    override fun copy(): Task {
        return copy()
    }
}