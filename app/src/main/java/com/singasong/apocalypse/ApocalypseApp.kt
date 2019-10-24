package com.singasong.apocalypse

import androidx.multidex.MultiDexApplication
import com.singasong.apocalypse.network.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

class ApocalypseApp : MultiDexApplication() {

    companion object {
        lateinit var instance: ApocalypseApp
            private set
    }

    private val appModule: Module = module {
        single { Retrofit.Builder().baseUrl("127.0.0.1:80").build().create(ApiService::class.java) }

        single {  }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@ApocalypseApp)
            modules(appModule)
        }
    }
}