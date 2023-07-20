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
import com.smarteist.autoimageslider.SliderView

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var imageurl:ArrayList<String>
    private lateinit var categoryList:ArrayList<Category>
    lateinit var sliderAdapter: SliderAdapter
    lateinit var categoryAdapter:CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom tool bar
        setSupportActionBar(binding.toobar.myToolbar)
        binding.toobar.notification.setOnClickListener {

        }

        //card recyclerView

        binding.recyclerView.layoutManager=GridLayoutManager(this,3)
        categoryList= ArrayList()
        categoryList.add(Category(R.drawable.product,"Product Brief","#62CEF9" ))
        categoryList.add(Category(R.drawable.memo,"Memo/Circular","#4AC989"))
        categoryList.add(Category(R.drawable.wpm,"Digital WPM","#A28EEC"))
        categoryList.add(Category(R.drawable.servay,"Survey","#FF7070"))
        categoryList.add(Category(R.drawable.quiz,"Exam/Quiz","#ECAC4A"))
        categoryList.add(Category(R.drawable.campaign,"Campaign","#FB80BA"))
        categoryList.add(Category(R.drawable.feedback,"Feedback","#62CEF9"))
        categoryList.add(Category(R.drawable.cycleplan,"Cycle Plan","#4AC989"))
        categoryList.add(Category(R.drawable.notice,"Notice","#A28EEC"))
        categoryAdapter= CategoryAdapter(categoryList)
        binding.recyclerView.adapter=categoryAdapter


        categoryAdapter.OnItemClick={
            val intent= Intent(this, DetailsActivity::class.java)
            intent.putExtra("category",it.title)
            startActivity(intent)
        }


        //slider image
        imageurl=ArrayList()
        imageurl.add("https://media.licdn.com/dms/image/C4D12AQEItqyuVjmQgg/article-cover_image-shrink_720_1280/0/1589412461241?e=2147483647&v=beta&t=ZY13S8Sk8G3Sunb6eYrT3jZGP2sv53Bxl0Ezk-Ws6yA")
        imageurl.add("https://media.nj.com/hunterdon-photos/photo/2016/04/21/-65feed4236d453a7.jpg")
        imageurl.add("https://tds-images.thedailystar.net/sites/default/files/styles/very_big_201/public/feature/images/2021/01/10/quality.jpg")
        sliderAdapter= SliderAdapter(imageurl)
        binding.imageSlider.autoCycleDirection= SliderView.LAYOUT_DIRECTION_LTR
        binding.imageSlider.setSliderAdapter(sliderAdapter)
        binding.imageSlider.isAutoCycle=true
        binding.imageSlider.startAutoCycle()
        binding.imageSlider.scrollTimeInSec=3
    }
}