package app.agenda.agendanotas.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(nota: Nota)

    @Delete
    suspend fun deleteNote(nota: Nota)

    @Query("select * from tablaNotas order by id desc")
    fun getAllNotes(): LiveData<List<Nota>>
}