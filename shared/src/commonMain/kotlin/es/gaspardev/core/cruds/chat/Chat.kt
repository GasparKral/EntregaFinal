package es.gaspardev.core.cruds.chat

import es.gaspardev.core.auxiliars.UseAddress
import es.gaspardev.utilities.Utils

class Chat(
    val chatId: String = Utils.generateRandomId(),
    val users: Pair<UseAddress, UseAddress>,
    val message: Map<UseAddress, ChatMessage> = mapOf()
) {


}