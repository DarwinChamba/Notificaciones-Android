package app.agenda.agendanotas.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Nota::class], version = 1, exportSchema = false)
abstract class NotaData:RoomDatabase() {
    abstract  fun getDao(): NotaDao
}