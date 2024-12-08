package com.example.examen_final_reclamos_einer_chavez

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReclamoAdapter(private var reclamos: List<Reclamo>) : RecyclerView.Adapter<ReclamoAdapter.ReclamoViewHolder>() {

    class ReclamoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fecha: TextView = itemView.findViewById(R.id.fecha)
        val sede: TextView = itemView.findViewById(R.id.sede)
        val plato: TextView = itemView.findViewById(R.id.plato)
        val mesa: TextView = itemView.findViewById(R.id.mesa)
        val monto: TextView = itemView.findViewById(R.id.monto)
        val atendido: TextView = itemView.findViewById(R.id.atendido)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReclamoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reclamos, parent, false)
        return ReclamoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReclamoViewHolder, position: Int) {
        val reclamo = reclamos[position]

        val formatoEntrada = SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy - HH:mm:ss", Locale("es", "ES"))
        val fechaDate: Date?

        try {
            fechaDate = formatoEntrada.parse(reclamo.fecha)
        } catch (e: ParseException) {
            holder.fecha.text = "Fecha no válida"
            return
        }

        holder.fecha.text = fechaDate?.let { formatearFecha(it) } ?: "Fecha no válida"
        holder.sede.text = "Sede: ${reclamo.sede}"
        holder.plato.text = "${reclamo.plato}"
        holder.mesa.text = "${reclamo.mesa}"
        holder.monto.text = "${reclamo.monto}"
        holder.atendido.text = "Atendido: ${reclamo.comensal}"

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleReclamoActivity::class.java).apply {
                putExtra("RECLAMO_DATA", reclamo) // Pasar el reclamo a la nueva actividad
            }
            context.startActivity(intent)
        }
    }

    private fun formatearFecha(fecha: Date): String {
        val formato = SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy", Locale("es", "ES"))
        return formato.format(fecha)
    }

    override fun getItemCount(): Int {
        return reclamos.size
    }

    fun setReclamos(nuevosReclamos: List<Reclamo>) {
        reclamos = nuevosReclamos
        notifyDataSetChanged()
    }
}
