package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.cruds.comments.Comment
import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.enums.TaskPriority
import es.gaspardev.core.enums.TaskStatus
import es.gaspardev.core.interfaces.Copyable
import es.gaspardev.utilities.Utils
import java.util.Date
import kotlin.reflect.KProperty0


data class Task(
    val id: String = Utils.generateRandomId(),
    val userCreator: User,
    var addressTo: UseAddress?,
    var addressFrom: UseAddress?,
    val creationDate: Date = Date(),
    var title: String?,
    var description: String?,
    var message: String?,
    var initDate: Date?,
    var dueDate: Date?,
    var completedDate: Date?,
    var priority: TaskPriority,
    var status: TaskStatus,
    var canGuestsSee: Boolean = false,
    var tags: HashSet<String> = HashSet(),
    var comments: HashSet<Comment> = HashSet()
) : Copyable {

    override fun copy(): Task {
        return Task(
            id = id,
            userCreator = userCreator,
            addressTo = addressTo,
            addressFrom = addressFrom,
            creationDate = creationDate,
            title = title,
            description = description,
            message = message,
            initDate = initDate,
            dueDate = dueDate,
            completedDate = completedDate,
            priority = priority,
            status = status,
            canGuestsSee = canGuestsSee,
            tags = tags,
            comments = comments
        )
    }

    operator fun <T> get(searchBy: KProperty0<T>): T {
        return searchBy.get()
    }


}