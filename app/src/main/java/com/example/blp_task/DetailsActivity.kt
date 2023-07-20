package com.example.blp_task

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.blp_task.R.id.back
import com.example.blp_task.R.id.my_toolbar

class DetailsActivity : AppCompatActivity() {

    private lateinit var mytoolbar:androidx.appcompat.widget.Toolbar
    private lateinit var back:ImageView
    private lateinit var title:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mytoolbar=findViewById(my_toolbar)
        back=findViewById(R.id.back)
        title=findViewById(R.id.title)
        back.isVisible=true
        setSupportActionBar(mytoolbar)


        title.text=intent.getStringExtra("category")

        back.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

    }
}