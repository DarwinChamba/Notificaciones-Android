package app.agenda.agendanotas.data.repository

import app.agenda.agendanotas.data.model.Nota
import app.agenda.agendanotas.data.model.NotaDao
import javax.inject.Inject

class NotaRepository @Inject constructor(
    private val dao:NotaDao
) {
    suspend fun insertNote(nota: Nota)=dao.insertNote(nota)

    fun getAllNotes()=dao.getAllNotes()
}