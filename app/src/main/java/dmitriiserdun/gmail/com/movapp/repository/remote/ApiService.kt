package dmitriiserdun.gmail.com.movapp.repository.remote

import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapperDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getLastPopularFilms(@Query("api_key") apiKey: String,@Query("page") page: Int?): Observable<PaginationWrapperDTO<MovieDTO>>
}