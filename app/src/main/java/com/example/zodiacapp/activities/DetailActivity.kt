package com.example.zodiacapp.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiacapp.HoroscopeViewHolder
import com.example.zodiacapp.R
import com.example.zodiacapp.data.HoroscopeProvider

class DetailActivity : AppCompatActivity() {
    lateinit var nameTextView: TextView
    lateinit var dateTextView: TextView
    lateinit var iconImagenView:  ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getStringExtra("HOROSCOPE_ID")!!
        val horoscope = HoroscopeProvider.getById(id)!!
        Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_SHORT).show()
    }


}