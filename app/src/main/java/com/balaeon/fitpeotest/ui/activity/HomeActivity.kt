package com.balaeon.fitpeotest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.balaeon.fitpeotest.R
import com.balaeon.fitpeotest.repository.PhotoRepository
import com.balaeon.fitpeotest.ui.viewmodel.PhotoViewModel
import com.balaeon.fitpeotest.ui.viewmodel.PhotoViewModelProviderFactory

class HomeActivity : AppCompatActivity() {

    lateinit var photoViewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val photoRepository=PhotoRepository()
        val photoViewModelProviderFactory=PhotoViewModelProviderFactory(photoRepository)
        photoViewModel= ViewModelProvider(this,photoViewModelProviderFactory)[PhotoViewModel::class.java]
    }
}