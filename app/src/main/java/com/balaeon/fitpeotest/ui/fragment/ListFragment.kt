package com.balaeon.fitpeotest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.balaeon.fitpeotest.R
import com.balaeon.fitpeotest.adapters.PhotoListAdapter
import com.balaeon.fitpeotest.databinding.FragmentListBinding
import com.balaeon.fitpeotest.ui.activity.HomeActivity
import com.balaeon.fitpeotest.ui.viewmodel.PhotoViewModel
import com.balaeon.fitpeotest.util.Resource

class ListFragment : Fragment() {

    lateinit var viewModel: PhotoViewModel

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var photoAdapter: PhotoListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as HomeActivity).photoViewModel
        setUpRecyclerView()
        photoAdapter.setOnItemClickListener { photoListResponseItem ->
            val bundle=Bundle().apply {
                putSerializable("photoItem",photoListResponseItem)
            }
            findNavController().navigate(R.id.action_listFragment_to_detailFragment,bundle)
        }
        viewModel.photoList.observe(viewLifecycleOwner, Observer { response->
            when(response)
            {
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {list->
                        photoAdapter.differ.submitList(list)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message->
                        Toast.makeText(context,"$message",Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility=View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility=View.VISIBLE
    }

    private fun setUpRecyclerView()
    {
        photoAdapter= PhotoListAdapter()
        binding.photoRecyclerView.apply {
            adapter=photoAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }
}