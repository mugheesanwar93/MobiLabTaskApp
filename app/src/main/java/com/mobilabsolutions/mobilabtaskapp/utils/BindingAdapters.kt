package com.mobilabsolutions.mobilabtaskapp.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.mobilabsolutions.mobilabtaskapp.R
import com.squareup.picasso.Picasso

class ImageBindingAdapter {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(imageView)
        }
    }

}
