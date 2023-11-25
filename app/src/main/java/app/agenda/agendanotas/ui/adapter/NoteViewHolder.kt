package app.agenda.agendanotas.ui.adapter

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.agenda.agendanotas.data.model.Nota
import app.agenda.agendanotas.databinding.ItemRecyclerListaBinding
import java.util.Calendar
import kotlin.math.min

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemRecyclerListaBinding.bind(view)
    private var countDownTimer: CountDownTimer? = null

    @SuppressLint("SetTextI18n")
    fun render(nota: Nota) {
        binding.title.text = nota.title
        binding.fecha.text = nota.fecha
        binding.hora.text = nota.hora
        val tiempo = nota.tiempo
        val tiempoActual = System.currentTimeMillis()
        val tiempoRestante = tiempo - tiempoActual
        val minutos=(tiempoRestante/60000).toInt()
        if(minutos>=0){
            binding.cuentaRegresiva.text="Faltan $minutos minutos"
        }

        countDownTimer=object :CountDownTimer(tiempoRestante,60000){
            override fun onTick(p0: Long) {
                val tiempoActualizado=(p0/60000).toInt()
                binding.cuentaRegresiva.text="Faltan $tiempoActualizado minutos"

            }

            override fun onFinish() {
                binding.cuentaRegresiva.text="Tiempo cumplido"
            }

        }.start()


    }
}