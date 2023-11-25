package app.agenda.agendanotas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablaNotas")
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description:String,
    val hora:String,
    val fecha:String,
    val tiempo: Long,
    val idNotification:Int
)