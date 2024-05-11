package com.example.vk_intern_mobile.core.messages

import com.example.vk_intern_mobile.core.messages.util_responses.AbilityInfoResponse
import kotlinx.serialization.Serializable

@Serializable
data class AbilityInfoItem(
    val effect_entries: List<AbilityInfoResponse>,
    val name: String
)
