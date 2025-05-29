package kr.toru.miscellaneous.app

import android.app.Application
import kr.toru.miscellaneous.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule)
        }
    }
}