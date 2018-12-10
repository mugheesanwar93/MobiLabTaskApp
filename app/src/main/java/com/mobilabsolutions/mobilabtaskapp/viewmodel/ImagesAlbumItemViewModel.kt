package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.app.Activity
import android.content.Context
import com.mobilabsolutions.mobilabtaskapp.pojo.Image

class ImagesAlbumItemViewModel(private val image: Image?, context: Context) : BaseViewModel(context as Activity) {

    //Passing image URL to XML for passing to Binding Adapters
    fun imageUrl(): String? {
        return image?.link
    }
}