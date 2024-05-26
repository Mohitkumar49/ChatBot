package com.example.chatbot.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object ChatData {

    private const val api_key = "AIzaSyBtPQG2TNshIZGkNe3DjTfMRdXq8gF1ju0"

    suspend fun getResponse(prompt: String): chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro", apiKey = api_key
        )

        try {
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }

            return chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            return chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }

    }


    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-image", apiKey = api_key
        )
        try {
            val input = content {
                image(bitmap)
                text(prompt)
            }
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(input)
            }

            return chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: Exception) {
            return chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )


        }

    }
}

