package com.ud.Viajes.Logic

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import com.ud.Viajes.views.SharedPreferencesManager

class GestorViajes(private val context: Context) {

    private val sharedPreferencesManager = SharedPreferencesManager(context)
    private val viajes = mutableStateListOf<Viaje>()

    init {
        viajes.addAll(sharedPreferencesManager.obtenerViajes())
        if (viajes.isEmpty()) {
            // Crear viajes de ejemplo solo si la lista está vacía
            val viaje1 = Viaje(
                1,
                "Cali",
                2000000.0,
                "20 de septiembre",
                "30 de septiembre",
                "Visitar el Parque de los Gatos para disfrutar de arte y naturaleza.\n" +
                        "Recorrer el barrio San Antonio y admirar su arquitectura colonial.\n" +
                        "Bailar salsa en una de las reconocidas escuelas de baile de la ciudad.\n" +
                        "Degustar un delicioso sancocho en una fonda local.\n" + "Explorar el Zoológico de Cali para conocer la fauna colombiana.",
                "El Parque de los Gatos\n"
                        + "El Cristo Rey\n" + "El barrio San Antonio\n"
                        + "El Zoológico de Cali\n"
                        + "La Catedral de San Pedro Apóstol"
            )
            val viaje2 = Viaje(
                2,
                "Medellin",
                2500000.0,
                "20 de octubre",
                "30 de octubre",
                "Disfrutar de una caminata en el Parque del Chicamocha y apreciar su belleza natural.\n"
                        + "Visitar el Parque de los Niños para un día de diversión en familia.\n"
                        + "Probar las arepas de huevo en un restaurante local.\n"
                        + "Recorrer el Centro Histórico y admirar su arquitectura colonial.\n"
                        + "Subir al mirador de la mesa de los santos para vistas panorámicas.",
                "Parque del Chicamocha\n"
                        + "Parque de los Niños\n"
                        + "Centro Histórico\n"
                        + "Mesa de los Santos\n"
                        + "Catedral Metropolitana de Bucaramanga"
            )
            val viaje3 = Viaje(
                3,
                "Bucaramanga",
                800000.0,
                "20 de noviembre",
                "30 de noviembre",
                "Visitar el Coliseo y el Foro Romano para revivir la historia.\n" +
                        "Explorar la Ciudad del Vaticano y admirar la Basílica de San Pedro.\n" +
                        "Lanzar una moneda en la Fontana di Trevi.\n" +
                        "Disfrutar de la gastronomía italiana en el Trastevere.\n" +
                        "Visitar las Catacumbas de Roma.",
                "Coliseo\n" +
                        "Foro Romano\n" +
                        "Ciudad del Vaticano\n" +
                        "Fontana di Trevi\n" +
                        "Trastevere\n" +
                        "Catacumbas de Roma"
            )
            viajes.addAll(listOf(viaje1, viaje2, viaje3))
            sharedPreferencesManager.guardarViajes(viajes)
        }
    }

    fun obtenerViajes(): List<Viaje> {
        return viajes
    }

    fun filtrarViajes(textoBusqueda: MutableState<String>, viajes: List<Viaje>): List<Viaje> {
        val viajesFiltrados = mutableListOf<Viaje>()
        if (textoBusqueda.value != "") {
            for (viaje in viajes) {
                if (viaje.destino.lowercase().contains(textoBusqueda.value.lowercase())) {
                    viajesFiltrados.add(viaje)
                }
            }
        } else {
            viajesFiltrados.addAll(viajes)
        }
        return viajesFiltrados
    }

    fun agregarViaje(viaje: Viaje) {
        viajes.add(viaje)
        sharedPreferencesManager.guardarViajes(viajes)
    }

    fun modificarViaje(viaje: Viaje) {
        val indice = viajes.indexOfFirst { it.idViaje == viaje.idViaje }
        if (indice != -1) {
            viajes[indice] = viaje
            sharedPreferencesManager.guardarViajes(viajes)
        }
    }

    fun buscarViaje(idViaje: Int): Viaje? {
        return viajes.find { it.idViaje == idViaje }
    }

    fun eliminarViaje(viaje: Viaje) {
        viajes.remove(viaje)
        sharedPreferencesManager.guardarViajes(viajes)
    }
}