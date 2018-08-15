package com.android.testtube

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.detail_list.*
import okhttp3.*
import java.io.IOException

class DetailListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_list)


        detailRecyclerView.layoutManager = LinearLayoutManager(this)
        detailRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        val toolbar = intent.getStringExtra("ACTION_BAR")
        supportActionBar?.title = toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        fetchData()
    }

    private fun fetchData(){
        val id = intent.getIntExtra("VIDEO_ID", 0)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$id"

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                println("fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()

                val courseLessons = gson.fromJson(body, Array<CourseLesson>::class.java)


                runOnUiThread {
                    detailRecyclerView.adapter = DetailListAdapter(courseLessons)
                }

            }

        })

    }
}