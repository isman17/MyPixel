package com.dicoding.capstone.core.di

import androidx.room.Room
import com.dicoding.capstone.core.BuildConfig
import com.dicoding.capstone.core.data.PixelRepository
import com.dicoding.capstone.core.data.source.local.LocalDataSource
import com.dicoding.capstone.core.data.source.local.room.PixelDatabase
import com.dicoding.capstone.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.core.data.source.remote.network.ApiService
import com.dicoding.capstone.core.domain.repository.IPixelRepository
import com.dicoding.capstone.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<PixelDatabase>().pixelDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("Dicoding Capstone".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            PixelDatabase::class.java,
            "Pixel.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = BuildConfig.HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/rtVIe78rCKMEx6j6iE+Kkmmgd1EuYzqDC5UO1iZFafs=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IPixelRepository> {
        PixelRepository(
            get(),
            get(),
            get()
        )
    }
}