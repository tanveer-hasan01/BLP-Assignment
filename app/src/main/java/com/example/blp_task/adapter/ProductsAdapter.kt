package com.example.blp_task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blp_task.databinding.ItemRecyclerviewBinding
import com.example.blp_task.dataclass.ProductsItem

class ProductsAdapter(val context: Context, var productList: List<ProductsItem>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root)


    fun setFilterList(mList: List<ProductsItem>){

        this.productList=mList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val binding = ItemRecyclerviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {

        holder.binding.title.text= productList[position].title
        holder.binding.subtitle.text= productList[position].subtitle
        holder.binding.date.text= productList[position].create_date

        Glide.with(holder.itemView)
            .load(productList[position].image).fitCenter()
            .into(holder.binding.medImage)



    }

    override fun getItemCount(): Int {
      return productList.size
    }
}