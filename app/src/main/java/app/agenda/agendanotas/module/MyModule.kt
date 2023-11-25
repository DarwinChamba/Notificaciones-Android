package app.agenda.agendanotas.module

import android.content.Context
import androidx.room.Room
import app.agenda.agendanotas.data.model.NotaData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class MyModule {
    @Singleton
    @Provides
    fun getprovidesData(@ApplicationContext context:Context)=
        Room.databaseBuilder(context.applicationContext,
        NotaData::class.java,
        "notaData").build()

    @Singleton
    @Provides
    fun getProvidesDao(data: NotaData)=data.getDao()
}