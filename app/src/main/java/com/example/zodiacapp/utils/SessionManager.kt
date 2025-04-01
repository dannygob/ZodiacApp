package com.example.zodiacapp.utils

import android.content.Context

class SessionManager (context: Context){
    private val SharePref = context.getSharedPreferences("zodiaK_session", Context.MODE_PRIVATE)


    fun setFavoriteHoroscope(id: String) {

        val editor = SharePref.edit()
        editor.putString("FAVORITE_HOROSCOPE", id)
        editor.apply()
    }
        fun getFavoriteHoroscope(): String {
            return SharePref.getString("FAVORITE_HOROSCOPE", "")!!

    }
}