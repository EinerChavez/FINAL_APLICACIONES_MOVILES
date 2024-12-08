package com.example.examen_final_reclamos_einer_chavez

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegistroReclamoActivity : AppCompatActivity() {

    private lateinit var reclamoViewModel: ReclamoViewModel
    private lateinit var adapter: ReclamoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reclamos)

        reclamoViewModel = ViewModelProvider(this).get(ReclamoViewModel::class.java)

        val recyclerReclamos = findViewById<RecyclerView>(R.id.recyclerReclamos)
        adapter = ReclamoAdapter(emptyList())
        recyclerReclamos.adapter = adapter
        recyclerReclamos.layoutManager = LinearLayoutManager(this)

        reclamoViewModel.reclamos.observe(this) { reclamos ->
            adapter.setReclamos(reclamos)
        }

        reclamoViewModel.registroExitoso.observe(this) { exitoso ->
            if (exitoso) {
                Toast.makeText(this, "Reclamo registrado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al registrar el reclamo", Toast.LENGTH_SHORT).show()
            }
        }

        val floatingButton = findViewById<FloatingActionButton>(R.id.floatingButton)
        floatingButton.setOnClickListener {
            registerReclamo()
        }
    }

    private fun registerReclamo() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_register, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        val edtSede = mDialogView.findViewById<EditText>(R.id.edtSede)
        val edtComensal = mDialogView.findViewById<EditText>(R.id.edtComercial)
        val edtPlato = mDialogView.findViewById<EditText>(R.id.edtPlato)
        val edtMesa = mDialogView.findViewById<EditText>(R.id.edtMesa)
        val edtMonto = mDialogView.findViewById<EditText>(R.id.edtMonto)
        val edtMozo = mDialogView.findViewById<EditText>(R.id.edtMozo)
        val edtReclamo = mDialogView.findViewById<EditText>(R.id.edtActividades)
        val btnRegistrar = mDialogView.findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            mAlertDialog.dismiss()
            val sede = edtSede.text.toString()
            val comensal = edtComensal.text.toString()
            val plato = edtPlato.text.toString()
            val mesa = edtMesa.text.toString()
            val monto = edtMonto.text.toString()
            val mozo = edtMozo.text.toString()
            val reclamoTexto = edtReclamo.text.toString()

            if (sede.isNotEmpty() && comensal.isNotEmpty() && plato.isNotEmpty() &&
                mesa.isNotEmpty() && monto.isNotEmpty() && mozo.isNotEmpty() && reclamoTexto.isNotEmpty()
            ) {
                reclamoViewModel.registrarReclamo(sede, comensal, plato, mesa, monto, mozo, reclamoTexto)
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
