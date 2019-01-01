package dmitriiserdun.gmail.com.movapp.repository.remote

import dmitriiserdun.gmail.com.movapp.repository.dto.Movie
import dmitriiserdun.gmail.com.movapp.repository.dto.PaginationWrapper
import dmitriiserdun.gmail.com.movapp.tools.onBackgroundThread
import dmitriiserdun.gmail.com.movapp.tools.onMainThread
import io.reactivex.Observable

class RemoteRepositoryImpl(var services: ApiService, var apiKey: String) : RemoteRepository {
    override fun getLastPopularFilms(): Observable<PaginationWrapper<Movie>> {
        return onBackgroundThread(services.getLastPopularFilms(apiKey))
    }

}