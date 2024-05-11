package com.example.vk_intern_mobile.core.messages

import com.example.vk_intern_mobile.core.messages.util_responses.StatResponse
import kotlinx.serialization.Serializable

@Serializable
data class StatItem(
    val effort: Int,
    val base_stat: Int,
    val stat: StatResponse
)