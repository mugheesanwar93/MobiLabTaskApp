package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.app.Activity
import android.databinding.ObservableField
import android.widget.CheckBox
import com.mobilabsolutions.mobilabtaskapp.adapter.ImagesAlbumAdapter
import com.mobilabsolutions.mobilabtaskapp.listener.ToolbarListener
import com.mobilabsolutions.mobilabtaskapp.pojo.Data
import com.mobilabsolutions.mobilabtaskapp.pojo.Image
import com.mobilabsolutions.mobilabtaskapp.pojo.ToolbarModel
import com.mobilabsolutions.mobilabtaskapp.ui.ImageDetailsActivity

class ImageDetailsViewModel(imageDetailsActivity: ImageDetailsActivity, private val data: Data?) : BaseViewModel(imageDetailsActivity), ToolbarListener {

    var toolbar = ObservableField<ToolbarModel>()

    val activity: Activity

    //Setting Visibility for Album or Single Image
    var isAlbum = checkIsAlbum(data?.images)

    //Setting Visibility for Availability of Description
    var isDescription = checkDescription(data?.description)

    //Setting value for Bottom Nav Bar
    var upVoteCount = ObservableField<String>(data!!.ups.toString())
    var downVoteCount = ObservableField<String>(data!!.downs.toString())
    var favouritesCount = ObservableField<String>(data!!.favoriteCount.toString())

    val adapter: ImagesAlbumAdapter

    private var imagesList: ArrayList<Image?>? = data?.images

    //Constructor
    init {
        setupToolbar()
        activity = imageDetailsActivity
        adapter = ImagesAlbumAdapter(imagesList)
        adapter.notifyDataSetChanged()
    }

    //Checking if item is Album
    // Not using isAlbum parameters because value incorrect, instead checking through Images ArrayList's size
    private fun checkIsAlbum(images: ArrayList<Image?>?): Boolean {
        return if (images != null)
            images.size > 1
        else
            false
    }

    //Setting visibility of description if value empty
    private fun checkDescription(description: String?): Boolean {
        return !description.equals("")
    }

    //Passing image URL to XML for passing to Binding Adapters
    fun imageUrl(): String? {
        return if (data?.images != null) {
            data.images!![0]?.link
        } else {
            data?.link
        }
    }

    //Setting Description in XML
    val description: String?
        get() = data?.description

    //Setting Views in XML
    val views: String?
        get() = data?.views.toString() + " Views"

    //Setting Score in XML
    val score: String?
        get() = data?.score.toString() + " Score"

    //Function to set dummy counter for UpVote & Meet UpVote Conditions
    fun onUpVotePressed(cbUpVote: CheckBox, cbDownVote: CheckBox) {
        if (cbUpVote.isChecked) {
            cbDownVote.isChecked = false
            upVoteCount.set(data?.ups?.plus(1).toString())
            downVoteCount.set(data?.downs.toString())
        } else {
            upVoteCount.set(data?.ups.toString())
        }
    }

    //Function to set dummy counter for DownVote & Meet DownVote Conditions
    fun onDownVotePressed(cbUpVote: CheckBox, cbDownVote: CheckBox) {
        if (cbDownVote.isChecked) {
            cbUpVote.isChecked = false
            upVoteCount.set(data?.ups.toString())
            downVoteCount.set(data?.downs?.plus(1).toString())
        } else {
            downVoteCount.set(data?.downs.toString())
        }
    }

    //Function to set dummy counter for Favorite & Meet Favorite Conditions
    fun onFavouritePressed(cbFavourite: CheckBox, cbUpVote: CheckBox, cbDownVote: CheckBox) {
        if (cbFavourite.isChecked) {
            cbUpVote.isChecked = true
            cbDownVote.isChecked = false
            favouritesCount.set(data?.favoriteCount?.plus(1).toString())
            upVoteCount.set(data?.ups?.plus(1).toString())
        } else {
            favouritesCount.set(data?.favoriteCount.toString())
        }
    }

    //Setup Toolbar Model with required Values
    private fun setupToolbar() {
        toolbar.set(ToolbarModel(
                data?.title,
                this))
    }

    //Custom Listener to handle Back Button in Toolbar
    override fun onBackIconListener() {
        activity.finish()
    }

}