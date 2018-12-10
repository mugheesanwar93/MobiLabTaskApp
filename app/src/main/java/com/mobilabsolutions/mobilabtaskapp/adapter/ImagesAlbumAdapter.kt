package com.mobilabsolutions.mobilabtaskapp.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.databinding.HolderImageAlbumBinding
import com.mobilabsolutions.mobilabtaskapp.pojo.Image
import com.mobilabsolutions.mobilabtaskapp.viewmodel.ImagesAlbumItemViewModel

class ImagesAlbumAdapter(private val imagesList: ArrayList<Image?>?) : RecyclerView.Adapter<ImagesAlbumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val holderImageAlbumBinding = DataBindingUtil.inflate<HolderImageAlbumBinding>(LayoutInflater.from(viewGroup.context), R.layout.holder_image_album, viewGroup, false)
        return ViewHolder(holderImageAlbumBinding)
    }

    override fun getItemCount(): Int {
        return imagesList!!.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindAlbum(imagesList!![i])
    }

    inner class ViewHolder(private val holderImageAlbumBinding: HolderImageAlbumBinding) : RecyclerView.ViewHolder(holderImageAlbumBinding.root) {
        fun bindAlbum(image: Image?) {
            holderImageAlbumBinding.album = ImagesAlbumItemViewModel(image, itemView.context)
        }
    }
}