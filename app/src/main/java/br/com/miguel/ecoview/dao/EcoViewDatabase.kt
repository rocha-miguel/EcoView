package br.com.miguel.ecoview.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.miguel.ecoview.model.HistoricoCO2
import br.com.miguel.ecoview.model.Usuario

@Database(
    entities = [Usuario::class, HistoricoCO2::class],
    version = 2,
    exportSchema = false
)
abstract class EcoViewDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun historicoCo2Dao(): HistoricoCo2Dao

    companion object {

        private lateinit var instance: EcoViewDatabase

        fun getDatabase(context: Context): EcoViewDatabase {

            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        EcoViewDatabase::class.java,
                        "ecoview_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance
        }
    }
}