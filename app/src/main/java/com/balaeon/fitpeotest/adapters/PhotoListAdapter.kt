package com.balaeon.fitpeotest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.balaeon.fitpeotest.R
import com.balaeon.fitpeotest.data.response.PhotoListResponse
import com.balaeon.fitpeotest.data.response.PhotoListResponseItem
import com.balaeon.fitpeotest.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding=ItemPhotoBinding.bind(itemView)
    }

    private val diffCallBack= object : DiffUtil.ItemCallback<PhotoListResponseItem>(){
        override fun areItemsTheSame(
            oldItem: PhotoListResponseItem,
            newItem: PhotoListResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotoListResponseItem,
            newItem: PhotoListResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ=AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo,parent,false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem=differ.currentList[position]
        holder.itemView.apply {
            setOnClickListener{
                onItemClickListener?.let { it(photoItem) }
            }
        }
        with(holder)
        {
            Picasso.get()
                .load(photoItem.thumbnailUrl)
                .into(binding.imageView)
            binding.textView.text= photoItem.title
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener :((PhotoListResponseItem)->Unit)?=null

    fun setOnItemClickListener(listener:(PhotoListResponseItem)->Unit){
        onItemClickListener=listener
    }
}