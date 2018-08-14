package com.android.testtube

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_item_3.view.*

class DetailListAdapter(val courseLessons: Array<CourseLesson>): RecyclerView.Adapter<DetailListHolder>() {

    fun ImageView.setImageUrl(url: String) =
            Glide.with(this).load(Uri.parse(url)).into(this)


    override fun getItemCount(): Int {
        return courseLessons.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListHolder {
        val layoutIntlater = LayoutInflater.from(parent.context)
        val listItem = layoutIntlater.inflate(R.layout.detail_item_3, parent, false)
        return DetailListHolder(listItem)
    }

    override fun onBindViewHolder(holder: DetailListHolder, position: Int) {
        val itemCourseLesson = courseLessons.get(position)

        holder.v.detailItemPic.setImageUrl(itemCourseLesson.imageUrl)
        holder.v.detailItemInfo.text = itemCourseLesson.name
        holder.v.duration.text = itemCourseLesson.duration
        holder.courseLesson = itemCourseLesson

    }
}

class DetailListHolder(val v: View, var courseLesson: CourseLesson? = null):RecyclerView.ViewHolder(v){

    init {
        v.setOnClickListener {
            val intent = Intent(v.context, CourseLessonActivity::class.java)
            intent.putExtra("LINK", courseLesson?.link)
            v.context.startActivity(intent)
        }
    }

}