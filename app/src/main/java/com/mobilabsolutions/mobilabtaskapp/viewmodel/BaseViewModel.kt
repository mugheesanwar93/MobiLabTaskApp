package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.app.Activity
import android.databinding.BaseObservable
import com.mobilabsolutions.mobilabtaskapp.network.RestService


open class BaseViewModel(private val mContext: Activity) : BaseObservable() {
    companion object {
        var restService: RestService? = null
    }
}
