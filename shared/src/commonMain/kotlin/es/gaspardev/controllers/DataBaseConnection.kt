package es.gaspardev.controllers

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.enums.UserStatus
import uk.gibby.driver.Surreal
import uk.gibby.driver.rpc.query
import uk.gibby.driver.rpc.signin
import uk.gibby.driver.rpc.use

object DataBaseConnection {

    private val DATABASE_URL = "http://localhost:8080/"
    private val PORT = 8080
    private val DATABASE_NAMESPACE = "tasks"
    private val DATABASE_NAME = "tasks"
    private val userName = ""
    private val userPassword = ""

    private val db = Surreal("localhost", PORT)

    private suspend fun connect() {
        db.connect()
        db.signin(userName, userPassword)
        db.use(DATABASE_NAMESPACE, DATABASE_NAME)
    }

    suspend fun getTasks(callingUser: UseAddress.User): HashSet<Task> {

        this.connect()
        val result = db.query(
            """
               SELECT * FROM tasks WHERE user_id = ${callingUser.user.id}
           """
        )

        return HashSet()
    }

    fun getAddresses(): HashSet<UseAddress> {
        return HashSet()
    }

    fun addNewTask(task: Task) {

    }

    fun addNewAddress(address: UseAddress) {

    }

    fun deleteTask(task: Task) {}

    suspend fun updateTask(task: Task) {

    }

    suspend fun updateUserConnectionState(user: User, state: UserStatus) {
        this.connect()
        db.query(
            """
                UPDATE user SET status = ${state.name} WHERE id = ${user.id}
            """
        )
    }

}