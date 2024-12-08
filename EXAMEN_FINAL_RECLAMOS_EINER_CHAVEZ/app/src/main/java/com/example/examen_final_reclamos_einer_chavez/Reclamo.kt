package com.example.examen_final_reclamos_einer_chavez

import java.io.Serializable


data class Reclamo(
    val sede: String="",
    val comensal: String="",
    val plato: String="",
    val mesa: String ="",
    val monto: String ="",
    val mozo: String ="",
    val reclamo: String ="",
    val fecha: String =""
) : Serializable
