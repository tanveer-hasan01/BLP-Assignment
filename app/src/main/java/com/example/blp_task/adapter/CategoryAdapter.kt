package com.example.blp_task.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.blp_task.R
import com.example.blp_task.dataclass.Category

class CategoryAdapter(private val categoryList:ArrayList<Category>)
    : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {


    var OnItemClick: ((Category) -> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_card_category,parent,false)
    return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {


        val category=categoryList[position]
        holder.imageView.setImageResource(category.image)
        holder.title.text = category.title
        holder.cardView.setCardBackgroundColor(Color.parseColor(category.color))

        holder.itemView.setOnClickListener {
            OnItemClick?.invoke(category)
        }

    }

    class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.imageView)
        val title:TextView=itemView.findViewById(R.id.title)
        val cardView:CardView=itemView.findViewById(R.id.category_card)
    }

}
