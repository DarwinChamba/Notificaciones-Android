package app.agenda.agendanotas.ui.view.dialogpicker

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePicker(val listener:(Long,Boolean)->Unit):DialogFragment(),TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val calendar=Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,p1)
        calendar.set(Calendar.MINUTE,p2)

        val isAM = calendar.get(Calendar.AM_PM) == Calendar.AM
        listener(calendar.timeInMillis,isAM)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar=Calendar.getInstance()
        val hora=calendar.get(Calendar.HOUR_OF_DAY)
        val minuto=calendar.get(Calendar.MINUTE)
        val dialog=TimePickerDialog(activity as Context,this,hora,minuto,false)
        return dialog
    }

}