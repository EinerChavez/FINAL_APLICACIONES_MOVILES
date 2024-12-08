package com.example.examen_final_reclamos_einer_chavez

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReclamoViewModel : ViewModel() {

    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    val registroExitoso = MutableLiveData<Boolean>()

    private val _reclamos = MutableLiveData<List<Reclamo>>(emptyList())
    val reclamos: LiveData<List<Reclamo>> get() = _reclamos

    init {
        obtenerReclamos()
    }

    fun registrarReclamo(sede: String, comensal: String, plato: String, mesa: String,
                         monto: String, mozo: String, reclamo: String) {

        val fechaActual = obtenerFechaActual()

        val nuevoReclamo = Reclamo(
            sede = sede,
            comensal = comensal,
            plato = plato,
            mesa = mesa,
            monto = monto,
            mozo = mozo,
            reclamo = reclamo,
            fecha = fechaActual
        )

        firestore.collection("Reclamos")
            .add(nuevoReclamo)
            .addOnSuccessListener {
                registroExitoso.value = true
                obtenerReclamos()
            }
            .addOnFailureListener { e ->
                registroExitoso.value = false
            }
    }

    private fun obtenerFechaActual(): String {
        val fechaFormat = SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy", Locale("es", "ES"))
        val horaFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val fechaActual = fechaFormat.format(Date())
        val horaActual = horaFormat.format(Date())

        return "$fechaActual - $horaActual"
    }

    private fun obtenerReclamos() {
        firestore.collection("Reclamos")
            .get()
            .addOnSuccessListener { documents ->
                val reclamosList = documents.mapNotNull { document ->
                    document.toObject(Reclamo::class.java)
                }
                _reclamos.value = reclamosList
            }
            .addOnFailureListener { e ->
                _reclamos.value = emptyList()
            }
    }
}
