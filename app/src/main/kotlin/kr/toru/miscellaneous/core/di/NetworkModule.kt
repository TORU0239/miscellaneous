package kr.toru.miscellaneous.core.di

import kr.toru.miscellaneous.core.network.client.createHttpClient
import kr.toru.miscellaneous.core.network.service.ApiService
import kr.toru.miscellaneous.core.network.service.ApiServiceImpl
import org.koin.dsl.module

val networkModule = module {
    single {
        createHttpClient()
    }

    single<ApiService> {
        ApiServiceImpl(get())
    }
}
