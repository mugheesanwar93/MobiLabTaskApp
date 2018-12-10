package com.mobilabsolutions.mobilabtaskapp.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.databinding.ActivityAboutBinding
import com.mobilabsolutions.mobilabtaskapp.viewmodel.AboutViewModel

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        val activityAboutBinding = DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about)
        val aboutViewModel = AboutViewModel(this)
        activityAboutBinding.about = aboutViewModel
    }
}
