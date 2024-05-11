package com.example.vk_intern_mobile.core.messages

import com.example.vk_intern_mobile.core.messages.util_responses.AbilityResponse
import kotlinx.serialization.Serializable

@Serializable
data class AbilityItem(
    val ability: AbilityResponse,
    val is_hidden: Boolean,
    val slot: Int
)
