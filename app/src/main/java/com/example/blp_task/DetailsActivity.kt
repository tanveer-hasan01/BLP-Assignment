package com.example.blp_task

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isVisible
import com.example.blp_task.R.id.back
import com.example.blp_task.R.id.my_toolbar
import com.example.blp_task.databinding.ActivityDetailsBinding
import com.example.blp_task.databinding.MyToobarBinding
import com.example.blp_task.dataclass.ProductsItem
import com.example.blp_task.network.ApiInterface
import com.example.blp_task.network.RetrofitInstance
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var bindingToolbar: MyToobarBinding
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingToolbar = MyToobarBinding.inflate(layoutInflater)

        //toobar
        setSupportActionBar(binding.toobar.myToolbar)
        binding.toobar.back.isVisible=true
        binding.toobar.title.text=intent.getStringExtra("category")
        binding.toobar.back.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.toobar.notification.setOnClickListener {
            Toast.makeText(this,"Not Implemented..",Toast.LENGTH_LONG).show()
        }

        binding.cardView.setBackgroundResource(R.drawable.sergel);


        //call api
        val medApi = RetrofitInstance.getInstance().create(ApiInterface::class.java)

        medApi.getProducts().enqueue(object : Callback<List<ProductsItem>?> {
            override fun onResponse(
                call: Call<List<ProductsItem>?>,
                response: Response<List<ProductsItem>?>
            ) {
                Snackbar.make(binding.root, ""+response.body()!!.size, Snackbar.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<ProductsItem>?>, t: Throwable) {

                Snackbar.make(binding.root, "Data not found", Snackbar.LENGTH_SHORT).show()
            }
        })


    }
}