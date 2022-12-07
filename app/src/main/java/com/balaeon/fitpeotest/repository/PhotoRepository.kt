package com.balaeon.fitpeotest.repository

import com.balaeon.fitpeotest.api.RetrofitInstance

class PhotoRepository() {
    suspend fun getPhotos()=RetrofitInstance.api.getListPhotos()
}