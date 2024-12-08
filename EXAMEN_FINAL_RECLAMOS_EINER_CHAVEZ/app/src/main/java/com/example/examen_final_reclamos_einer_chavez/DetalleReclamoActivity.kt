package com.example.examen_final_reclamos_einer_chavez

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleReclamoActivity : AppCompatActivity() {

    private lateinit var txtSede: TextView
    private lateinit var txtPlato: TextView
    private lateinit var txtMesa: TextView
    private lateinit var txtMonto: TextView
    private lateinit var txtMozo: TextView
    private lateinit var txtReclamo: TextView
    private lateinit var txtFecha: TextView
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        txtSede = findViewById(R.id.txtSede)
        txtPlato = findViewById(R.id.txtPlato)
        txtMesa = findViewById(R.id.txtMesa)
        txtMonto = findViewById(R.id.txtMonto)
        txtMozo = findViewById(R.id.atendido)
        txtReclamo = findViewById(R.id.txtComentarios)
        txtFecha = findViewById(R.id.txtFecha)
        btnVolver = findViewById(R.id.btnCerrar)

        val reclamo = intent.getSerializableExtra("RECLAMO_DATA") as? Reclamo

        if (reclamo != null) {
            txtSede.text = "Sede: ${reclamo.sede}"
            txtPlato.text = "${reclamo.plato}"
            txtMesa.text = " ${reclamo.mesa}"
            txtMonto.text = " ${reclamo.monto}"
            txtMozo.text = "Atendido: ${reclamo.mozo}"
            txtReclamo.text = "Detalle Reclamo: ${reclamo.reclamo}"
            txtFecha.text = "Fecha: ${reclamo.fecha}"
        } else {
            txtSede.text = "Error al cargar los datos"
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}
