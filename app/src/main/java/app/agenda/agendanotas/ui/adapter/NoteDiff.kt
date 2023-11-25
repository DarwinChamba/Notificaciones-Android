package app.agenda.agendanotas.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import app.agenda.agendanotas.data.model.Nota

class NoteDiff:DiffUtil.ItemCallback<Nota>() {
    override fun areItemsTheSame(oldItem: Nota, newItem: Nota): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Nota, newItem: Nota): Boolean {
        return oldItem==newItem
    }
}