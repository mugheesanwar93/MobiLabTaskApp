package com.mobilabsolutions.mobilabtaskapp.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.databinding.ActivityImageDetailsBinding
import com.mobilabsolutions.mobilabtaskapp.pojo.Data
import com.mobilabsolutions.mobilabtaskapp.viewmodel.ImageDetailsViewModel

class ImageDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        val activityImageDetailsBinding = DataBindingUtil.setContentView<ActivityImageDetailsBinding>(this, R.layout.activity_image_details)
        val imageDetailsViewModel = ImageDetailsViewModel(this, intent.getParcelableExtra<Parcelable>("galleryData") as Data?)
        activityImageDetailsBinding.detail = imageDetailsViewModel
    }
}
