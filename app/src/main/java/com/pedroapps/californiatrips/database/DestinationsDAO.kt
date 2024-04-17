package com.pedroapps.californiatrips.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinationsDAO {

    @Query("SELECT * FROM destinations_table")
    fun getAllDestinations() : Flow<List<Destination>?>

    //TODO(find someone that might know what the best way to do this is? this works, but I wonder if I'm creating too many flows)
    @Query("SELECT * FROM destinations_table WHERE destination_name LIKE :destinationName")
    fun getDestinationByName(destinationName: String): Flow<Destination>

    @Insert(entity = Destination::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewDestination(destination: Destination)

    @Update(entity = Destination::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDestination(destination: Destination)

    @Delete(entity = Destination::class)
    suspend fun deleteDestination(destination: Destination)
}