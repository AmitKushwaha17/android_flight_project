package com.example.intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageSliderAdapter (private val images : List<Int>) : RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder>(){
    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.imageView)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageSliderAdapter.SliderViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider,parent,false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSliderAdapter.SliderViewHolder, position: Int) {
      holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size



}