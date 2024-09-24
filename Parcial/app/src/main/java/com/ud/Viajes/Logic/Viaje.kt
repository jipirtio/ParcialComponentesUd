package com.ud.Viajes.Logic

/**
 * Clase que representa un viaje.
 */
data class Viaje(
    val idViaje: Int = 0,
    var destino: String = "",
    var presupuesto: Double = 0.0,
    var fechaInicio: String = "",
    var fechaFin: String = "",
    var actividades: String = "",
    var lugares: String = ""
)