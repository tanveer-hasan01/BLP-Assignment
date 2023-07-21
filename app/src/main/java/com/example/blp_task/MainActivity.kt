package com.example.blp_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blp_task.adapter.CategoryAdapter
import com.example.blp_task.adapter.SliderAdapter
import com.example.blp_task.databinding.ActivityDetailsBinding
import com.example.blp_task.databinding.ActivityMainBinding
import com.example.blp_task.dataclass.Category
import com.example.blp_task.dataclass.SliderItem
import com.example.blp_task.network.ApiInterface
import com.example.blp_task.network.RetrofitInstance
import com.google.android.material.snackbar.Snackbar
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var imageurl: ArrayList<SliderItem>
    private lateinit var categoryList: ArrayList<Category>
    lateinit var sliderAdapter: SliderAdapter
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom tool bar
        setSupportActionBar(binding.toobar.myToolbar)
        binding.toobar.notification.setOnClickListener {

        }

        //card recyclerView

        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        categoryList = ArrayList()
        categoryList.add(Category(R.drawable.product, "Product Brief", "#62CEF9"))
        categoryList.add(Category(R.drawable.memo, "Memo/Circular", "#4AC989"))
        categoryList.add(Category(R.drawable.wpm, "Digital WPM", "#A28EEC"))
        categoryList.add(Category(R.drawable.servay, "Survey", "#FF7070"))
        categoryList.add(Category(R.drawable.quiz, "Exam/Quiz", "#ECAC4A"))
        categoryList.add(Category(R.drawable.campaign, "Campaign", "#FB80BA"))
        categoryList.add(Category(R.drawable.feedback, "Feedback", "#62CEF9"))
        categoryList.add(Category(R.drawable.cycleplan, "Cycle Plan", "#4AC989"))
        categoryList.add(Category(R.drawable.notice, "Notice", "#A28EEC"))
        categoryAdapter = CategoryAdapter(categoryList)
        binding.recyclerView.adapter = categoryAdapter


        categoryAdapter.OnItemClick = {
            if (it.title == "Product Brief") {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("category", it.title)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    it.title + " is not implement yet",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }


        //slider image
        getSliderImage()

    }

    private fun getSliderImage() {

        imageurl = ArrayList()

        val medApi = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        medApi.getSliderImage().enqueue(object : Callback<List<SliderItem>?> {
            override fun onResponse(
                call: Call<List<SliderItem>?>,
                response: Response<List<SliderItem>?>
            ) {
                val responseBody = response.body()
                imageurl.addAll(responseBody!!)
                sliderAdapter = SliderAdapter(imageurl)
                binding.imageSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                binding.imageSlider.setSliderAdapter(sliderAdapter)
                binding.imageSlider.isAutoCycle = true
                binding.imageSlider.startAutoCycle()
                binding.imageSlider.scrollTimeInSec = 3

            }

            override fun onFailure(call: Call<List<SliderItem>?>, t: Throwable) {
                Snackbar.make(binding.root, "Data not found", Snackbar.LENGTH_SHORT).show()
            }
        })


    }
}