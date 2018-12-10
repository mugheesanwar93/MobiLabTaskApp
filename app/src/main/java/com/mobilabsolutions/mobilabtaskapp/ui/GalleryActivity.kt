package com.mobilabsolutions.mobilabtaskapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.databinding.ActivityGalleryBinding
import com.mobilabsolutions.mobilabtaskapp.viewmodel.GalleryViewModel

class GalleryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        val activityGalleryBinding = DataBindingUtil.setContentView<ActivityGalleryBinding>(this, R.layout.activity_gallery)
        val galleryViewModel = GalleryViewModel(this)
        activityGalleryBinding.gallery = galleryViewModel
        setSupportActionBar(activityGalleryBinding.tbToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.about -> {
                transitionActivities(this, AboutActivity::class.java, false)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
