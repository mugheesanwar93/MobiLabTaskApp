package com.mobilabsolutions.mobilabtaskapp.network

import com.mobilabsolutions.mobilabtaskapp.pojo.GalleryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {

    @GET("gallery/{section}/{sort}/{window}?")
    fun getGallery(@Path(value = "section") section: String,
                   @Path(value = "sort") sort: String,
                   @Path(value = "window") window: String,
                   @Query("showViral") showViral: Boolean): Observable<GalleryResponse>
}
