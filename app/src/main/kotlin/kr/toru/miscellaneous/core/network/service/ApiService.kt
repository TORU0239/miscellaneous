package kr.toru.miscellaneous.core.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kr.toru.miscellaneous.core.network.model.request.NameRqDTO
import kr.toru.miscellaneous.core.network.model.response.NameRsDTO

interface ApiService {
    suspend fun fetchSampleData(
        request: NameRqDTO
    ): NameRsDTO
}

class ApiServiceImpl(private val client: HttpClient): ApiService {

    override suspend fun fetchSampleData(
        request: NameRqDTO
    ): NameRsDTO {

        val result = runCatching {
            client.get(
                "https://api.agify.io"
            ){
                url {
                    parameter("name", request.name)
                }
            }.body<NameRsDTO>()
        }

        result.getOrNull()?.let {
            return it
        } ?: run {
            throw Exception("Failed to fetch sample data")
        }
    }
}