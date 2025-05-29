package kr.toru.miscellaneous.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String
)
