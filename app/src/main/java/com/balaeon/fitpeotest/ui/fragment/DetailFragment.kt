package com.balaeon.fitpeotest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.balaeon.fitpeotest.R
import com.balaeon.fitpeotest.databinding.FragmentDetailBinding
import com.balaeon.fitpeotest.ui.activity.HomeActivity
import com.balaeon.fitpeotest.ui.viewmodel.PhotoViewModel
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    lateinit var viewModel: PhotoViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    val args:DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as HomeActivity).photoViewModel
        val getPhotoItem=args.photoItem
        Picasso.get()
            .load(getPhotoItem.url)
            .into(binding.imageView2)
        binding.textView2.text=getPhotoItem.title
    }
}