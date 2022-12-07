package com.balaeon.fitpeotest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balaeon.fitpeotest.data.response.PhotoListResponse
import com.balaeon.fitpeotest.repository.PhotoRepository
import com.balaeon.fitpeotest.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PhotoViewModel(
    val repository : PhotoRepository
):ViewModel() {

    val photoList:MutableLiveData<Resource<PhotoListResponse>> = MutableLiveData()
    var photoListPage=1

    init {
        getPhotos()
    }

    fun getPhotos()=viewModelScope.launch {
        photoList.postValue(Resource.Loading())
        val response=repository.getPhotos()
        photoList.postValue(handlePhotoListResponse(response))
    }

    private fun handlePhotoListResponse(response:Response<PhotoListResponse>):Resource<PhotoListResponse>{
        if (response.isSuccessful)
        {
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}