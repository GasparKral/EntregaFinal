package es.gaspardev.core.cruds.task

import es.gaspardev.core.interfaces.Deleter
import java.util.Date
import kotlin.reflect.KProperty1

object TaskDeleter : Deleter<Task> {

    private val deletedTask: ArrayList<Task> = arrayListOf()

    override fun delete(value: Task) {
        deletedTask.add(value)
    }

    override fun undo(): Task {
        return deletedTask.removeLast()
    }

    override fun undo(index: Int): Task {
        return deletedTask.removeAt(index)
    }

    override fun clear() {
        deletedTask.clear()
    }

    fun searchByID(id: String): Task {
        return deletedTask[getIndexOf(Task::id, id)]
    }

    fun searchByTitle(title: String): Task {
        return deletedTask[getIndexOf(Task::title, title)]
    }

    fun searchByDate(date: Date): List<Task> {
        return deletedTask.filter { it.dueDate == date || it.creationDate == date }
    }

    private fun <S> getIndexOf(searchBy: KProperty1<Task, S>, searchValue: S): Int {
        return deletedTask.indexOf(deletedTask.find { searchBy.get(it) == searchValue })
    }
}