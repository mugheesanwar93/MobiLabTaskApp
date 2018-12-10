package com.mobilabsolutions.mobilabtaskapp.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.databinding.HolderGalleryBinding
import com.mobilabsolutions.mobilabtaskapp.pojo.Data
import com.mobilabsolutions.mobilabtaskapp.viewmodel.GalleryItemViewModel
import java.util.*

class GalleryAdapter(private var galleryList: ArrayList<Data?>?) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GalleryAdapter.ViewHolder {
        val holderGalleryBinding = DataBindingUtil.inflate<HolderGalleryBinding>(LayoutInflater.from(viewGroup.context), R.layout.holder_gallery, viewGroup, false)
        return ViewHolder(holderGalleryBinding)
    }

    override fun getItemCount(): Int {
        return galleryList!!.size
    }

    override fun onBindViewHolder(viewHolder: GalleryAdapter.ViewHolder, i: Int) {
        viewHolder.bindGallery(galleryList!![i])
    }

    inner class ViewHolder(private val holderGalleryBinding: HolderGalleryBinding) : RecyclerView.ViewHolder(holderGalleryBinding.root) {
        fun bindGallery(data: Data?) {
            holderGalleryBinding.gallery = GalleryItemViewModel(data, itemView.context)
        }
    }

    //Functions for Testing
    //region
    fun getGalleryList(): ArrayList<Data?>? {
        return galleryList
    }

    fun setGallery(galleryList: ArrayList<Data?>?) {
        this.galleryList = galleryList
        notifyDataSetChanged()
    }

    fun addGalleryItem(galleryItem: Data) {
        galleryList?.add(galleryItem)
        notifyItemInserted(galleryList!!.indexOf(galleryItem))
    }

    fun removeFavoritedItem(galleryItemToRemove: Data): Boolean {
        val index = galleryList?.indexOf(galleryItemToRemove)
        if (index!! < 0) return false
        galleryList?.removeAt(index)
        notifyItemRemoved(index)
        return true
    }
    //endregion
}