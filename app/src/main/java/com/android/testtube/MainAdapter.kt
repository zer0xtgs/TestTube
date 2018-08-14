package com.android.testtube

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    fun ImageView.setImageUrl(url: String) =
            Glide.with(this).load(Uri.parse(url)).into(this)

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.desctiption.text = homeFeed.videos.get(position).name
        holder.view.imageView.setImageUrl(homeFeed.videos.get(position).imageUrl)
        holder.view.channelPic.setImageUrl(homeFeed.videos.get(position).channel.profileImageUrl)
        holder.view.channelName.text = homeFeed.videos.get(position).channel.name
        holder.video = homeFeed.videos.get(position)
    }


}

class CustomViewHolder(val view : View, var video: Video? = null): RecyclerView.ViewHolder(view){

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetailListActivity::class.java)
            intent.putExtra("ACTION_BAR", video?.name)
            intent.putExtra("VIDEO_ID", video?.id)
            view.context.startActivity(intent)
        }
    }

}


//private fun ImageView.setImageUrl(url: String) =
//        Glide.with(this)
//                .load(Uri.parse(url))
//                .apply(RequestOptions()
//                        .centerCrop()
//                        .diskCacheStrategy(DiskCacheStrategy.DATA))
//                .into(this)