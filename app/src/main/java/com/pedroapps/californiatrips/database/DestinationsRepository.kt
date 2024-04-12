package com.pedroapps.californiatrips.database

import android.app.Application
import kotlinx.coroutines.flow.Flow

class DestinationsRepository(application: Application) {

    private val database = DestinationsDatabase.getInstance(application)
    private val destinationsDao = database.destinationDao()


    fun getAllDestinations() : Flow<List<Destination>?> {
        return destinationsDao.getAllDestinations()
    }

    fun getDestinationByName(name: String): Flow<Destination> {
        return destinationsDao.getDestinationByName(name)
    }

    suspend fun createNewDestination(destination: Destination) {
        destinationsDao.createNewDestination(destination)
    }

    suspend fun updateDestination(destination: Destination) {
        destinationsDao.updateDestination(destination)
    }

    suspend fun deleteDestination(destination: Destination) {
        destinationsDao.deleteDestination(destination)
    }

}