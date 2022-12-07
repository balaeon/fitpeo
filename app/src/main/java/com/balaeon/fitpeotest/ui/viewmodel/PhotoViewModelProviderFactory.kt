package com.balaeon.fitpeotest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.balaeon.fitpeotest.repository.PhotoRepository

class PhotoViewModelProviderFactory(
    val photoRepository: PhotoRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoViewModel(photoRepository) as T
    }

}