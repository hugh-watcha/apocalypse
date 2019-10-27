package com.singasong.apocalypse

import androidx.lifecycle.SavedStateHandle
import androidx.multidex.MultiDexApplication
import com.singasong.apocalypse.data.AppRepository
import com.singasong.apocalypse.data.Repository
import com.singasong.apocalypse.data.pref.PreferenceHelper
import com.singasong.apocalypse.network.ApiService
import com.singasong.apocalypse.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
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

        single { PreferenceHelper(androidContext()) }

        single<Repository> { AppRepository(get()) }

        viewModel(useState =  true) { (handle: SavedStateHandle) -> MainViewModel(get(), handle) }
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