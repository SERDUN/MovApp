package dmitriiserdun.gmail.com.movapp.repository.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getCities(@Query("api_key") lat: Double): Observable<List<Any>>
}