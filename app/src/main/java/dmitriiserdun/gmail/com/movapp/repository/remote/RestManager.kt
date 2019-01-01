package dmitriiserdun.gmail.com.movapp.repository.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dmitriiserdun.gmail.com.movapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestManager {
    private var service: ApiService
    private lateinit var retrofit: Retrofit
    var api: RemoteRepository
    var gson = GsonBuilder()
        .setLenient()
        .create()


    init {
        service = createService()
        api = RemoteRepositoryImpl(service,BuildConfig.API_KEY)

    }



    private fun createService(): ApiService {
        val httpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }


        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory .create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        httpClientBuilder.addInterceptor(logging)
        builder.client(httpClientBuilder.connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).build())
        retrofit = builder.build()
        return retrofit.create(ApiService::class.java)
    }

}