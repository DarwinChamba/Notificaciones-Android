package app.agenda.agendanotas.ui.view.fragment

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.agenda.agendanotas.R
import app.agenda.agendanotas.data.model.Nota
import app.agenda.agendanotas.data.util.Constants.Companion.DESCRIPTION
import app.agenda.agendanotas.data.util.Constants.Companion.MY_CHANNEL_ID
import app.agenda.agendanotas.data.util.Constants.Companion.TITLE
import app.agenda.agendanotas.data.util.Constants.Companion.requestCodeRandom
import app.agenda.agendanotas.databinding.FragmentCrearBinding
import app.agenda.agendanotas.ui.view.dialogpicker.DatePicker
import app.agenda.agendanotas.ui.view.dialogpicker.TimePicker
import app.agenda.agendanotas.ui.view.notification.MyNotification
import app.agenda.agendanotas.ui.viewmodel.NotaViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CrearFragment : Fragment() {
    private lateinit var binding: FragmentCrearBinding
    private lateinit var model: NotaViewModel
    var minuteOfDay: Int = 0
    var hourOfDay: Int = 0
    var yearSeleted: Int = 0
    var monthSelected: Int = 0
    var daySelected: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrearBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity()).get(NotaViewModel::class.java)
        binding.fecha.setOnClickListener { createDate() }
        binding.hora.setOnClickListener { createTime() }
        binding.guardarNotas.setOnClickListener {
            scheduleNotification()
            validarDatos()
        }
        createChannel()
        return binding.root
    }


    private fun createTime() {
        val time = TimePicker { hora,isAmPm->getTime(hora,isAmPm) }
        time.show(childFragmentManager, "childTime")
    }

    @SuppressLint("SetTextI18n")
    private fun getTime(hora: Long,isAMpM:Boolean) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = hora
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        minuteOfDay = calendar.get(Calendar.MINUTE)

        val valor=if(isAMpM)"AM" else "PM"

        if (hourOfDay != 0 && minuteOfDay != 0) {
            binding.tvtimeSelected.visibility = View.VISIBLE
            binding.tvhora.text = "Hora Seleccionada:"
            binding.tvtimeSelected.text = "$hourOfDay:$minuteOfDay $valor"
        }


    }

    private fun createDate() {
        val dialog = DatePicker { year, mes, dia -> getDate(year, mes, dia) }
        dialog.show(childFragmentManager, "childFragment")
    }

    @SuppressLint("SetTextI18n")
    private fun getDate(year: Int, month: Int, day: Int) {

        val monthOfYear = monthOfYear(month)
        yearSeleted = year
        monthSelected = month
        daySelected = day
        if (yearSeleted != 0) {
            binding.tvdateSeleted.visibility = View.VISIBLE
            binding.tvfecha.text = "Fecha Seleccionada:"
            binding.tvdateSeleted.text = "$daySelected de $monthOfYear del $yearSeleted"
        } else {
            binding.tvdateSeleted.visibility = View.INVISIBLE
            binding.tvfecha.text = "Fecha NO Seleccionada:"
        }


    }

    private fun scheduleNotification() {
        val calendar = Calendar.getInstance()
        calendar.set(yearSeleted, monthSelected, daySelected, hourOfDay, minuteOfDay)
        val intent = Intent(requireContext(), MyNotification::class.java)
        intent.putExtra(TITLE,binding.title.text.toString())
        intent.putExtra(DESCRIPTION,binding.description.text.toString())
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(), requestCodeRandom, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun monthOfYear(month: Int): String {
        return when (month + 1) {
            1 -> "Enero"
            2 -> "Febrero"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Mayo"
            6 -> "Junio"
            7 -> "Julio"
            8 -> "Agosto"
            9 -> "Septiembre"
            10 -> "Octubre"
            11 -> "Noviembre"
            else -> "Dicimebre"

        }
    }



    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MY_CHANNLEiD",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as
                        NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun validarDatos() {
        val calendar = Calendar.getInstance()
        calendar.set(yearSeleted, monthSelected, daySelected, hourOfDay, minuteOfDay)
        val title = binding.title.text.toString()
        val description = binding.description.text.toString()
        val fecha = obtenerFecha()
        val hora = obtenerHora()
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(requireContext(), "Titulo requerido", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(description)) {
            Toast.makeText(requireContext(), "descripcion requerida requerido", Toast.LENGTH_SHORT)
                .show()
        } else {
            val nota =
                Nota(0, title, description, hora, fecha, calendar.timeInMillis, requestCodeRandom)
            model.insertarNota(nota)
            Toast.makeText(requireContext(), "Tarea ingresada con exito", Toast.LENGTH_SHORT).show()
            binding.title.setText("")
            binding.tvtimeSelected.text = " "
            binding.tvdateSeleted.text=""
            binding.description.setText("")

        }


    }

    private fun obtenerFecha(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(calendar.time)
    }

    private fun obtenerHora(): String {
        val date = Date().time
        val dateFormat = SimpleDateFormat("HH:mm")
        return dateFormat.format(date)
    }


}