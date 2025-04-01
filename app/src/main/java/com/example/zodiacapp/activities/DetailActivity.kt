package com.example.zodiacapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiacapp.R
import com.example.zodiacapp.data.Horoscope
import com.example.zodiacapp.data.HoroscopeProvider
import com.example.zodiacapp.utils.SessionManager

class DetailActivity : AppCompatActivity() {
    lateinit var nameTextView: TextView
    lateinit var datesTextView: TextView
    lateinit var iconImagenView: ImageView
    lateinit var session: SessionManager
    lateinit var horoscope: Horoscope
    lateinit var favoriteMenuItem: MenuItem
    var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        session = SessionManager(this)
        nameTextView = findViewById(R.id.nameTextView)
        datesTextView = findViewById(R.id.datesTextView)
        iconImagenView = findViewById(R.id.iconImageView)

        val id = intent.getStringExtra("HOROSCOPE_ID")!!
        horoscope = HoroscopeProvider.getById(id)
        session.getFavoriteHoroscope() == horoscope.id

        val horoscope = HoroscopeProvider.getById(id)!!
        //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_SHORT).show()
        isFavorite = session.getFavoriteHoroscope() == horoscope.id
        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.dates)
        iconImagenView.setImageResource(horoscope.icon)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)
        menu.findItem(R.id.menu_favorite)

        favoriteMenuItem = menu.findItem(R.id.menu_favorite)
        setFavoriteIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                Toast.makeText(this, "Favorito", Toast.LENGTH_SHORT).show()
                if (isFavorite) {
                    session.setFavoriteHoroscope("")

                } else {
                    session.setFavoriteHoroscope(horoscope.id)

                }
                isFavorite = !isFavorite
                setFavoriteIcon()

                return true
            }

            R.id.menu_share -> {
                // Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show()
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send. ")
                sendIntent.setType("text plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }

        }
    }

    fun setFavoriteIcon() {
        if (isFavorite) {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite_selected)
        } else {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite)

        }
    }

}
