package app.agenda.agendanotas.ui.view.dialogpicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import app.agenda.agendanotas.R
import java.util.Calendar

class DatePicker(val listener:(year:Int,mes:Int,dia:Int)->Unit):DialogFragment(),
     DatePickerDialog.OnDateSetListener {
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        listener(p1,p2,p3)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar=Calendar.getInstance()
        val dia=calendar.get(Calendar.DAY_OF_MONTH)
        val mes=calendar.get(Calendar.MONTH)
        val year=calendar.get(Calendar.YEAR)
        val dialog=DatePickerDialog(activity as Context , this,year,mes,dia)
        dialog.datePicker.minDate=Calendar.getInstance().timeInMillis
        return  dialog
    }

}