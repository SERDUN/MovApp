package dmitriiserdun.gmail.com.movapp.repository.remote

import dmitriiserdun.gmail.com.movapp.repository.dto.MovieDTO
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapperDTO
import dmitriiserdun.gmail.com.movapp.tools.onBackgroundThread
import io.reactivex.Observable

class RemoteRepositoryImpl(var services: ApiService, var apiKey: String) : RemoteRepository {
    override fun getLastPopularFilms(page: Int?): Observable<PaginationWrapperDTO<MovieDTO>> {
        return onBackgroundThread(services.getLastPopularFilms(apiKey,page))
    }

}