package es.gaspardev.core.cruds.chat

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.utilities.Utils

class Chat(
    val chatId: String = Utils.generateRandomId(),
    val users: Pair<UseAddress.User, UseAddress.User>,
    private val message: HashMap<ChatMessage, UseAddress> = HashMap()
) {

    fun addMessage(user: UseAddress, message: ChatMessage) {
        this.message[message] = user
    }

    fun getMessage(id: String): ChatMessage? {
        return message.keys.find { it.id == id }
    }
}