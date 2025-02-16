package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.enums.TaskPriority
import es.gaspardev.core.enums.TaskStatus
import es.gaspardev.core.interfaces.Builder
import java.util.*

object TaskBuilder : Builder<Task> {

    private var task: Task? = null

    override fun build(): Task {
        val tempTask: Task? = task
        reset()
        return tempTask!!
    }

    override fun reset() {
        this.task = null
    }

    override fun initialize(initialValue: Task?) {
        this.task = initialValue
    }

    fun newTask(creator: User) {
        task = Task(
            userCreator = creator,
            status = TaskStatus.TODO,
            addressTo = null,
            addressFrom = null,
            title = null,
            description = null,
            message = null,
            initDate = null,
            dueDate = null,
            completedDate = null,
            priority = TaskPriority.NOT_DEFINED
        )
    }

    fun setTitle(title: String) {
        task?.title = title
    }

    fun setDescription(description: String) {
        task?.description = description
    }

    fun setDueDate(dueDate: Date) {
        if (dueDate.before(
                Date(
                    task?.creationDate?.time?.minus((1000 * 60 * 60 * 24)) ?: 0
                )
            )
        ) throw IllegalArgumentException("Invalid due date")
        task?.dueDate = dueDate
    }

    fun setPriority(priority: TaskPriority) {
        task?.priority = priority
    }

    fun setMessage(message: String) {
        task?.message = message
    }

    fun setAddressTo(addressTo: UseAddress) {
        task?.addressTo = addressTo
    }

    fun setAddressFrom(addressFrom: UseAddress) {
        task?.addressFrom = addressFrom
    }

    fun setInitialDate(initialDate: Date) {
        if (initialDate.before(
                Date(
                    task?.creationDate?.time?.minus((1000 * 60 * 60 * 24)) ?: 0
                )
            )
        ) throw IllegalArgumentException("Invalid initial date")
        task?.initDate = initialDate
    }

}