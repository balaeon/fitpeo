package com.balaeon.fitpeotest.api

import com.balaeon.fitpeotest.data.response.PhotoListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListAPI {
    @GET("photos")
    suspend fun getListPhotos():Response<PhotoListResponse>
}