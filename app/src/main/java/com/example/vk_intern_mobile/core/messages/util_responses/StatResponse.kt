package com.example.vk_intern_mobile.core.messages.util_responses

import kotlinx.serialization.Serializable

@Serializable
data class StatResponse(
    val name: String,
    val url: String
)
