package es.gaspardev.core.cruds.chat

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.enums.ChatMessageType
import es.gaspardev.utilities.Utils
import java.util.Date

open class ChatMessage(
    val id: String = Utils.generateRandomId(),
    val user: UseAddress,
    var message: String,
    var date: Date,
    val messageType: ChatMessageType
) {


}