package com.mobilabsolutions.mobilabtaskapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import com.mobilabsolutions.mobilabtaskapp.R
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)
        init()
    }

    @SuppressLint("CheckResult")
    private fun init() {
        Completable.timer(3, TimeUnit.SECONDS)
                .subscribe { transitionActivities(this, GalleryActivity::class.java, true) }
    }
}
