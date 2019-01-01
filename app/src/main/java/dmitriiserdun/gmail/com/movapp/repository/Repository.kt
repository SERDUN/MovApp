package dmitriiserdun.gmail.com.movapp.repository

import dmitriiserdun.gmail.com.movapp.App
import dmitriiserdun.gmail.com.movapp.repository.remote.RemoteRepository
import dmitriiserdun.gmail.com.movapp.repository.remote.RestManager

object Repository {
    val remote: RemoteRepository by lazy { RestManager().api }

}