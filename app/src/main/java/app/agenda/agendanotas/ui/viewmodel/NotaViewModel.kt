package app.agenda.agendanotas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.agenda.agendanotas.data.model.Nota
import app.agenda.agendanotas.domain.GetAllNotesUseCase
import app.agenda.agendanotas.domain.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotaViewModel @Inject constructor(
    private val insertar:InsertNoteUseCase,
    private val getLista:GetAllNotesUseCase
):ViewModel() {
    fun insertarNota(nota: Nota)=viewModelScope.launch {
        insertar(nota)
    }
    fun getAllNotes()=getLista()
}