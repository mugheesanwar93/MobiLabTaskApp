package com.mobilabsolutions.mobilabtaskapp

import android.support.v7.widget.RecyclerView

import com.google.gson.Gson
import com.mobilabsolutions.mobilabtaskapp.adapter.GalleryAdapter
import com.mobilabsolutions.mobilabtaskapp.network.RestService
import com.mobilabsolutions.mobilabtaskapp.pojo.Data
import com.mobilabsolutions.mobilabtaskapp.pojo.GalleryResponse
import com.mobilabsolutions.mobilabtaskapp.utils.Constants
import com.mobilabsolutions.mobilabtaskapp.viewmodel.GalleryViewModel
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

import java.io.IOException
import java.util.ArrayList
import java.util.Arrays

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_SECTION
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_SHOW_VIRAL
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_SORT
import com.mobilabsolutions.mobilabtaskapp.utils.Constants.DEFAULT_WINDOW
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.mockito.Mockito.verify

class GalleryActivityUnitTest {
    private val galleryList = ArrayList(Arrays.asList(
            Data("1", "Test Title", "This is a test item", 123, "https://test.com", 321, 123, 231, 312, true, 123, null),
            Data("2", "Test Title 2", "This is a test item 2", 1234, "https://test2.com", 4321, 1234, 2341, 3412, true, 4123, null)
    ))


    private var galleryAdapter: GalleryAdapter? = null
    private var mMockAdapterDataObserver: RecyclerView.AdapterDataObserver? = null
    private val galleryViewModel: GalleryViewModel? = null

    //Setting up for Unit Test Environment
    @Before
    fun setUp() {
        galleryAdapter = GalleryAdapter(galleryList)

        mMockAdapterDataObserver = Mockito.mock(RecyclerView.AdapterDataObserver::class.java)
        galleryAdapter!!.registerAdapterDataObserver(mMockAdapterDataObserver!!)
        galleryAdapter!!.setGallery(galleryList)
    }


    @Test
    @Throws(IOException::class)
    fun getGalleryTest() {
        val mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url(Constants.BASE_URL).toString())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mockWebServer.enqueue(MockResponse().setBody(""))

        val service = retrofit.create(RestService::class.java)

        val callGetFollowers = service.getGallery(DEFAULT_SECTION, DEFAULT_SORT, DEFAULT_WINDOW, DEFAULT_SHOW_VIRAL)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        assertNotNull(callGetFollowers.isEmpty)

        mockWebServer.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun testGetItemCount() {
        assertEquals(2, galleryAdapter!!.itemCount.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun testGetItems() {
        assertEquals(galleryList, galleryAdapter!!.getGalleryList())
    }

    @Test
    @Throws(Exception::class)
    fun testAddItem() {
        val newGalleryItem = Data("3", "Test Title 3", "This is a test item 3", 12345, "https://test3.com", 54321, 12345, 23451, 34512, true, 45123, null)

        galleryAdapter!!.addGalleryItem(newGalleryItem)

        val indexOfItem = galleryAdapter!!.getGalleryList()!!.indexOf(newGalleryItem)
        assertTrue(indexOfItem > 0)
        verify<RecyclerView.AdapterDataObserver>(mMockAdapterDataObserver).onItemRangeInserted(indexOfItem, 1)
    }

    @Test
    @Throws(Exception::class)
    fun testRemoveItem() {
        val galleryItemToRemove = galleryList[0]
        val result = galleryAdapter!!.removeFavoritedItem(galleryItemToRemove)

        assertTrue(result)
        assertFalse(galleryAdapter!!.getGalleryList()!!.contains(galleryItemToRemove))

        verify<RecyclerView.AdapterDataObserver>(mMockAdapterDataObserver).onItemRangeRemoved(0, 1)
    }
}
