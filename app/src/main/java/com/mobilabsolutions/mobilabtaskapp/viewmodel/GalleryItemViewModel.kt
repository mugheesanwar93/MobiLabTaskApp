package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.mobilabsolutions.mobilabtaskapp.pojo.Data
import com.mobilabsolutions.mobilabtaskapp.ui.ImageDetailsActivity

class GalleryItemViewModel(private val galleryData: Data?, private val context: Context) : BaseViewModel(context as Activity) {

    //Setting Visibility for Description Availability
    var isDescription = checkDescriptionExists(galleryData?.description)

    //Passing image URL to XML for passing to Binding Adapters
    fun imageUrl(): String? {
        return getThumbnail(if (galleryData != null && !galleryData.isAlbum!!) {
            galleryData.link
        } else {
            galleryData!!.images!![0]!!.link
        })
    }

    //Updating link for Image Caching
    private fun getThumbnail(imageLink: String?): String? {
        val updatedImageLink = StringBuilder(imageLink).insert(imageLink!!.length - 4, "m").toString();
        return updatedImageLink
    }

    //Checking if Description Exists
    private fun checkDescriptionExists(description: String?): Boolean {
        return !description.equals("")
    }

    //Setting Title in XML
    val title: String
        get() {
            return galleryData?.title!!
        }

    //Opening Image Details
    fun onImageClicked() {
        val intent = Intent(context, ImageDetailsActivity::class.java).apply {
            putExtra("galleryData", galleryData)
        }
        context.startActivity(intent)
    }
}