package es.gaspardev.core.cruds.chat

import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.enums.ChatMessageType
import es.gaspardev.utilities.Utils
import java.util.*

class MessageReply(
    val replyToMessage: ChatMessage,
    id: String = Utils.generateRandomId(),
    message: String,
    date: Date,
    user: UseAddress
) : ChatMessage(
    id,
    user,
    message,
    date,
    ChatMessageType.REPLY
) {


}