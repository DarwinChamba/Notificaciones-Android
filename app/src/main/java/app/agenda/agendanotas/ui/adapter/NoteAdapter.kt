package app.agenda.agendanotas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import app.agenda.agendanotas.R

class NoteAdapter:RecyclerView.Adapter<NoteViewHolder>() {
    private val diffItem=NoteDiff()
    val differ=AsyncListDiffer(this,diffItem)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler_lista,parent,false
        ))
    }

    override fun getItemCount()=differ.currentList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.render(differ.currentList[position])
    }
}