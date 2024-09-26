package es.gaspardev.core.cruds.task

import es.gaspardev.core.auxiliars.CRUD


object TaskCrud : CRUD<Task>() {

    private var instance: TaskCrud? = null

    fun getInstance(): TaskCrud {
        if (instance == null) {
            instance = TaskCrud
        }
        return instance!!
    }


    override fun delete(value: Task) {
        TaskDeleter.delete(value)
        values.remove(value)
    }

    override fun modify() {
        val instance = TaskModifier.getInstance()
        val oldTaskIndex = values.indexOf(instance.getFirstState()!!)
        values[oldTaskIndex] = instance.getState()!!
    }

}