package com.example.zodiacapp.activities

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiacapp.R
import com.example.zodiacapp.data.Horoscope
import com.example.zodiacapp.data.HoroscopeProvider
import com.example.zodiacapp.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.HttpUrl
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    lateinit var nameTextView: TextView
    lateinit var datesTextView: TextView
    lateinit var iconImagenView: ImageView
    lateinit var session: SessionManager
    lateinit var horoscope: Horoscope

    var isFavorite = false
    lateinit var favoriteMenuItem: MenuItem


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

        horoscope = HoroscopeProvider.getById(id)!!

        isFavorite= session.getFavoriteHoroscope() == horoscope.id

        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.dates)
        iconImagenView.setImageResource(horoscope.icon)

        getHoroscopeLuck()
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
                //Toast.makeText(this, "Favorito", Toast.LENGTH_SHORT).show()
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
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
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
fun  getHoroscopeLuck(){
    progressBar.visibility
    CoroutineScope(Dispatchers.IO)
    Val url = url.openConection()


    try(
        if (urlConnetion.responseCode == HttpURLConnection.HTTP_OK){

            val bufferedReader = BufferedReader

            log.i("API",)
            return response.toString()



            val result JSONObject(response.toString()).getJSONObject("data").getString
        } else{
            log.it("API,")
            return "false"
        }

    )
}