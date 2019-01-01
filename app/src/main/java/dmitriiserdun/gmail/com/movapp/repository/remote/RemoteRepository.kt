package dmitriiserdun.gmail.com.movapp.repository.remote

import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapper
import io.reactivex.Observable

interface RemoteRepository {
    fun getLastPopularFilms(): Observable<PaginationWrapper<Movie>>

}