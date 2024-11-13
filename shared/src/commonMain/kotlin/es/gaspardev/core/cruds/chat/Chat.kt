package es.gaspardev.core.cruds.chat

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.utilities.Utils

class Chat(
    val chatId: String = Utils.generateRandomId(),
    val users: Pair<UseAddress, UseAddress>,
    private val message: HashMap<UseAddress, ChatMessage> = HashMap()
) {

    fun addMessage(user: UseAddress, message: ChatMessage) {
        this.message[user] = message
    }

    fun getMessage(id: String): ChatMessage? {
        return message.filter { it.value.id == id }.map { it.value }.firstOrNull()
    }
}