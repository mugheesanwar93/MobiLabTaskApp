package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.app.Activity
import android.content.pm.PackageManager
import android.databinding.ObservableField
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.listener.ToolbarListener
import com.mobilabsolutions.mobilabtaskapp.pojo.ToolbarModel
import com.mobilabsolutions.mobilabtaskapp.ui.AboutActivity


class AboutViewModel(aboutActivity: AboutActivity) : BaseViewModel(aboutActivity), ToolbarListener {
    val toolbar = ObservableField<ToolbarModel>()
    val activity: Activity

    init {
        activity = aboutActivity
        setupToolbar()
    }

    //Setting Version in XML
    val version: String
        get() {
            return try {
                val pInfo = activity.packageManager.getPackageInfo(activity.packageName, 0)
                "Version : " + pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                "Version : 1.0"
            }
        }


    private fun setupToolbar() {
        toolbar.set(ToolbarModel(
                activity.getString(R.string.about),
                this
        ))
    }

    override fun onBackIconListener() {
        activity.finish()
    }
}