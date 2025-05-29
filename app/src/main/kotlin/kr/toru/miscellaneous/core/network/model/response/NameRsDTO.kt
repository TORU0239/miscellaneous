package kr.toru.miscellaneous.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class NameRsDTO(
    val age: Int,
    val count: Int,
    val name: String
)