package com.example.chatbot


import android.graphics.Bitmap
import com.example.chatbot.data.chat

data class ChatState (
    val chatList: MutableList<chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)