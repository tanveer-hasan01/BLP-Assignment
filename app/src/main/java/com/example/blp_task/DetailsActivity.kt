package com.example.blp_task

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.widget.Adapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blp_task.R.id.back
import com.example.blp_task.R.id.my_toolbar
import com.example.blp_task.adapter.ProductsAdapter
import com.example.blp_task.databinding.ActivityDetailsBinding
import com.example.blp_task.databinding.MyToobarBinding
import com.example.blp_task.dataclass.Category
import com.example.blp_task.dataclass.ProductsItem
import com.example.blp_task.network.ApiInterface
import com.example.blp_task.network.RetrofitInstance
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class DetailsActivity : AppCompatActivity() {

    private lateinit var bindingToolbar: MyToobarBinding
    private lateinit var binding: ActivityDetailsBinding
    lateinit var adapter: ProductsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var productList:ArrayList<ProductsItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingToolbar = MyToobarBinding.inflate(layoutInflater)

        //toobar
        setSupportActionBar(binding.toobar.myToolbar)
        binding.toobar.back.isVisible = true
        binding.toobar.title.text = intent.getStringExtra("category")
        binding.toobar.back.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.toobar.notification.setOnClickListener {
            Toast.makeText(this, "Not Implemented..", Toast.LENGTH_LONG).show()
        }

        binding.cardView.setBackgroundResource(R.drawable.sergel);

        productList= ArrayList()
        linearLayoutManager = LinearLayoutManager(this)
        binding.productRecycler.layoutManager = linearLayoutManager

        //call api
        getProducts()

        binding.searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               filterList(p0!!)
                return true
            }

        })

    }


    private fun getProducts() {
        val medApi = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        medApi.getProducts().enqueue(object : Callback<List<ProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProductsItem>?>,
                response: Response<List<ProductsItem>?>
            ) {
                val responseBody = response.body()
                productList.addAll(responseBody!!)
                adapter = ProductsAdapter(baseContext, responseBody!!)
                adapter.notifyDataSetChanged()
                binding.productRecycler.adapter = adapter
                binding.progressBar.isVisible = false
            }

            override fun onFailure(call: Call<List<ProductsItem>?>, t: Throwable) {
                binding.progressBar.isVisible = false
                Snackbar.make(binding.root, "Data not found", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun filterList(query:String){
        if (query!=null){
            val filteredList = ArrayList<ProductsItem>()
            for (i in productList){
                if (i.title.toLowerCase(Locale.ROOT).contains(query)
                    ||i.subtitle.toLowerCase(Locale.ROOT).contains(query)||
                    i.create_date.toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()){

            }else{
                adapter.setFilterList(filteredList)
            }

        }
    }
}