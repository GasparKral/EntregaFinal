package es.gaspardev.core.cruds.chat

import es.gaspardev.core.auxiliars.UseAddress
import es.gaspardev.core.enums.ChatMessageType
import java.util.Date

class ChatMessage(
    val user: UseAddress,
    val message: String,
    val date: Date,
    val messageType: ChatMessageType
) {

}