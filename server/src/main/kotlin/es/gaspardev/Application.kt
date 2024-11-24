package es.gaspardev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.io.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        // Inicialización de la aplicación
        environment.monitor.subscribe(ApplicationStarted) {
            // Ejecutar el comando docker cuando el servidor inicie
            startSurrealDB()
        }

    }.start(wait = true)
}

fun startSurrealDB() {
    val command = listOf("docker", "compose", "up")

    try {
        val process = ProcessBuilder(command)
            .directory(File("./server/src/main/resources")) // Cambia el directorio de trabajo
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        println("SurrealDB está arrancando...")
        process.waitFor() // Espera que el comando termine, opcional
        println("SurrealDB ha iniciado.")
    } catch (e: IOException) {
        e.printStackTrace()
        println("Error al iniciar SurrealDB.")
    }
}