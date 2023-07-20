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
            Toast.makeText(this,"dfsdf",Toast.LENGTH_LONG).show()
        }

    }
}