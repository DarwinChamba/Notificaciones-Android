package app.agenda.agendanotas.domain

import app.agenda.agendanotas.data.model.Nota
import app.agenda.agendanotas.data.repository.NotaRepository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val repository: NotaRepository
) {
    suspend operator fun invoke(nota: Nota)=repository.insertNote(nota)
}