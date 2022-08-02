package com.pierre.data.remote.service

import com.pierre.data.remote.model.RemoteSong
import retrofit2.Response
import retrofit2.http.GET

interface RemoteService {

    @GET("/img/shared/technical-test.json")
    suspend fun songs(): Response<List<RemoteSong>>

}