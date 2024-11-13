package es.gaspardev.controllers

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.cruds.task.Task

object DataBaseConnection {

    private val DATABASE_URL = "http://localhost:8080/"
    private val DATABASE_NAME = "tasks"
    private val userName = ""
    private val userPassword = ""

    fun getTasks(): HashSet<Task> {
        return HashSet()
    }

    fun getAddresses(): HashSet<UseAddress> {
        return HashSet()
    }

    fun addNewTask(task: Task) {

    }

    fun addNewAddress(address: UseAddress) {

    }

}