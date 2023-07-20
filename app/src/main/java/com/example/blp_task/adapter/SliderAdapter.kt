package com.example.blp_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.blp_task.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter (imageurl:ArrayList<String>):
    SliderViewAdapter<SliderAdapter.Holder>()  {

    var sliderImage: ArrayList<String> =imageurl



    override fun getCount(): Int {
       return sliderImage.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): Holder {
        var inflator:View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_slider,null)
        return Holder(inflator)
    }

    override fun onBindViewHolder(viewHolder: Holder?, position: Int) {
        if (viewHolder!=null)
            Glide.with(viewHolder.itemView)
                .load(sliderImage.get(position)).fitCenter()
                .into(viewHolder.imageView)

    }

    class Holder(itemView:View):SliderViewAdapter.ViewHolder(itemView) {

        var imageView: ImageView=itemView.findViewById(R.id.myImage)
    }
}