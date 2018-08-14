package com.android.testtube

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.course_lesson_activity.*

class CourseLessonActivity:AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_lesson_activity)

        val courseLink = intent.getStringExtra("LINK")

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.loadUrl(courseLink)
    }
}