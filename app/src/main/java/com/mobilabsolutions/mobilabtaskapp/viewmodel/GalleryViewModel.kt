package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import com.mobilabsolutions.mobilabtaskapp.R
import com.mobilabsolutions.mobilabtaskapp.adapter.GalleryAdapter
import com.mobilabsolutions.mobilabtaskapp.databinding.DialogFilterBinding
import com.mobilabsolutions.mobilabtaskapp.listener.GallerySelectionListener
import com.mobilabsolutions.mobilabtaskapp.network.RetrofitClientInstance
import com.mobilabsolutions.mobilabtaskapp.pojo.Data
import com.mobilabsolutions.mobilabtaskapp.pojo.GalleryResponse
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_SECTION
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_SHOW_VIRAL
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_SORT
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_WINDOW
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class GalleryViewModel(val activity: Activity) : BaseViewModel(activity), GallerySelectionListener {

    val adapter: GalleryAdapter

    private var galleryList: ArrayList<Data?>? = null
    private var show: AlertDialog? = null

    //Setting current view to Linear Layout
    private var currentView = 3

    init {
        if (galleryList == null)
            galleryList = ArrayList()
        adapter = GalleryAdapter(galleryList)

        restService = RetrofitClientInstance.getRestService()
        callServices(DEFAULT_SECTION, DEFAULT_SORT, DEFAULT_WINDOW, DEFAULT_SHOW_VIRAL)
    }

    //Changing Views according to States selected
    fun switchView(rvGallery: RecyclerView, state: Int) {
        if (state != currentView) {
            currentView = state
            when (state) {
                1 -> {
                    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    rvGallery.layoutManager = layoutManager
                }
                2 -> {
                    val gridLayout = GridLayoutManager(activity, 2)
                    rvGallery.layoutManager = gridLayout
                }
                3 -> {
                    val listLayout = LinearLayoutManager(activity)
                    rvGallery.layoutManager = listLayout
                }
            }
        }
    }

    private fun callServices(section: String, sort: String, window: String, show_viral: Boolean) {
        val callGalleryService = restService?.getGallery(section, sort, window, show_viral)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
        callGalleryService?.subscribe(object : Observer<GalleryResponse> {

            override fun onComplete() {
                adapter.notifyDataSetChanged()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(response: GalleryResponse) {
                val list = response.data
                galleryList?.clear()
                galleryList?.addAll(list as ArrayList<Data>)
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    //Display Alert Dialog for Filter Options
    fun showFilter() {
        val dialog = AlertDialog.Builder(activity, R.style.CustomDialogTheme)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_filter, null)
        val filterDialogBinding = DialogFilterBinding.bind(view)
        val dialogViewModel = DialogViewModel(this, view)
        filterDialogBinding.dialog = dialogViewModel
        dialog.setView(filterDialogBinding.root)
        show = dialog.show()
    }

    //Implement Filter Value Changes
    override fun onSelected(section: String, sort: String, window: String, viral: Boolean) {
        callServices(section, sort, window, viral)
        show?.dismiss()
    }

}
