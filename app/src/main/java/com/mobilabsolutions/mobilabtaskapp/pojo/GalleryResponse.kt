package com.mobilabsolutions.mobilabtaskapp.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class GalleryResponse(
        @SerializedName("data")
        var `data`: ArrayList<Data?>?,
        @SerializedName("success")
        var success: Boolean?,
        @SerializedName("status")
        var status: Int?
)

data class Data(
        @SerializedName("id")
        var id: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("views")
        var views: Int?,
        @SerializedName("link")
        var link: String?,
        @SerializedName("ups")
        var ups: Int?,
        @SerializedName("downs")
        var downs: Int?,
        @SerializedName("points")
        var points: Int?,
        @SerializedName("score")
        var score: Int?,
        @SerializedName("is_album")
        var isAlbum: Boolean?,
        @SerializedName("favorite_count")
        var favoriteCount: Int?,
        @SerializedName("images")
        var images: ArrayList<Image?>?) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.createTypedArrayList(Image.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(title)
        writeString(description)
        writeValue(views)
        writeString(link)
        writeValue(ups)
        writeValue(downs)
        writeValue(points)
        writeValue(score)
        writeValue(isAlbum)
        writeValue(favoriteCount)
        writeTypedList(images)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Data> = object : Parcelable.Creator<Data> {
            override fun createFromParcel(source: Parcel): Data = Data(source)
            override fun newArray(size: Int): Array<Data?> = arrayOfNulls(size)
        }
    }
}


data class Image(
        @SerializedName("id")
        var id: String?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("datetime")
        var datetime: Int?,
        @SerializedName("type")
        var type: String?,
        @SerializedName("animated")
        var animated: Boolean?,
        @SerializedName("width")
        var width: Int?,
        @SerializedName("height")
        var height: Int?,
        @SerializedName("size")
        var size: Int?,
        @SerializedName("views")
        var views: Int?,
        @SerializedName("bandwidth")
        var bandwidth: Long?,
        @SerializedName("favorite")
        var favorite: Boolean?,
        @SerializedName("is_ad")
        var isAd: Boolean?,
        @SerializedName("in_most_viral")
        var inMostViral: Boolean?,
        @SerializedName("has_sound")
        var hasSound: Boolean?,
        @SerializedName("ad_type")
        var adType: Int?,
        @SerializedName("ad_url")
        var adUrl: String?,
        @SerializedName("in_gallery")
        var inGallery: Boolean?,
        @SerializedName("link")
        var link: String?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Long::class.java.classLoader) as Long?,
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readValue(Boolean::class.java.classLoader) as Boolean?,
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(description)
        writeValue(datetime)
        writeString(type)
        writeValue(animated)
        writeValue(width)
        writeValue(height)
        writeValue(size)
        writeValue(views)
        writeValue(bandwidth)
        writeValue(favorite)
        writeValue(isAd)
        writeValue(inMostViral)
        writeValue(hasSound)
        writeValue(adType)
        writeString(adUrl)
        writeValue(inGallery)
        writeString(link)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {
            override fun createFromParcel(source: Parcel): Image = Image(source)
            override fun newArray(size: Int): Array<Image?> = arrayOfNulls(size)
        }
    }
}