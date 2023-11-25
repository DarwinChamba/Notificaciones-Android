package app.agenda.agendanotas.domain

import app.agenda.agendanotas.data.repository.NotaRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NotaRepository
) {
    operator fun invoke()=repository.getAllNotes()
}