package com.pedroapps.californiatrips.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Destination::class], version = 1, exportSchema = false)
abstract class DestinationsDatabase: RoomDatabase() {

    abstract fun destinationDao(): DestinationsDAO

    companion object {

        private var databaseInstance : DestinationsDatabase? = null

        fun getInstance(context: Context): DestinationsDatabase {
            return databaseInstance ?: synchronized(this){
              val database =  Room.databaseBuilder(
                  context, DestinationsDatabase::class.java, "destinations_database")
                  .fallbackToDestructiveMigration()
                  .build()
                databaseInstance = database
                database
            }
        }
    }
}