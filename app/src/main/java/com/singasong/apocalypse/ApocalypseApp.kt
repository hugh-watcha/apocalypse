package com.singasong.apocalypse

import androidx.lifecycle.SavedStateHandle
import androidx.multidex.MultiDexApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.singasong.apocalypse.data.AppRepository
import com.singasong.apocalypse.data.Repository
import com.singasong.apocalypse.data.pref.PreferenceHelper
import com.singasong.apocalypse.network.ApiService
import com.singasong.apocalypse.network.AppExecutors
import com.singasong.apocalypse.network.LiveDataCallAdapterFactory
import com.singasong.apocalypse.viewmodel.MainViewModel
import com.singasong.apocalypse.viewmodel.PostsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApocalypseApp : MultiDexApplication() {

    companion object {
        lateinit var instance: ApocalypseApp
            private set
    }

    private val appModule: Module = module {
        single {
            OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    }
                }
                .build()
        }

        single {
            Retrofit.Builder().baseUrl("http://10.0.2.2:80")
                .client(get())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setFieldNamingPolicy(
                            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
                        ).create()
                    )
                )
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build().create(ApiService::class.java)
        }

        single { PreferenceHelper(androidContext()) }

        single { AppExecutors() }

        single<Repository> { AppRepository(get(), get(), get()) }

        viewModel(useState = true) { (handle: SavedStateHandle) -> MainViewModel(get(), handle) }
        viewModel(useState = true) { (handle: SavedStateHandle) ->
            PostsViewModel(
                get(),
                handle
            )
        }
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