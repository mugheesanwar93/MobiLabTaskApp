package com.mobilabsolutions.mobilabtaskapp.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {
    protected fun transitionActivities(current: Activity, newActivity: Class<*>, isFinished: Boolean) {
        val intent = Intent(current, newActivity)
        startActivity(intent)
        if (isFinished) {
            current.finish()
        }
    }
}
