package id.djaka.flicker.injection.module

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.util.BASE_URL
import id.djaka.flicker.util.SharedKey
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson



@Module
@Suppress("unused")
object NetworkModule {
    @Provides @Reusable @JvmStatic
    internal  fun provideRetrofitInterface(c:Context): Retrofit{
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        val pref = c.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE)
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + pref.getString(SharedKey.Session.TOKEN, "")!!)
                .build()
            chain.proceed(newRequest)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides @Reusable @JvmStatic
    internal  fun provideApiService(retrofit: Retrofit,c:Context): ApiServices{
        return retrofit.create(ApiServices::class.java)
    }
}
