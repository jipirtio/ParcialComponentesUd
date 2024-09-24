package com.ud.Viajes.views

import android.content.Context
import com.google.gson.Gson
import com.ud.Viajes.Logic.Viaje

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("viajes", Context.MODE_PRIVATE)

    fun guardarViajes(viajes: List<Viaje>) {
        val editor = sharedPreferences.edit()
        editor.putString("viajes", Gson().toJson(viajes))
        editor.apply()
    }

    fun obtenerViajes(): List<Viaje> {
        val viajesJson = sharedPreferences.getString("viajes", null)
        return if (viajesJson != null) {
            Gson().fromJson(viajesJson, Array<Viaje>::class.java).toList()
        } else {
            emptyList()
        }
    }
}