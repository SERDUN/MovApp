package dmitriiserdun.gmail.com.movapp.repository.remote

import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapperDTO
import io.reactivex.Observable

interface RemoteRepository {
    fun getLastPopularFilms(page: Int?): Observable<PaginationWrapperDTO<MovieDTO>>

}