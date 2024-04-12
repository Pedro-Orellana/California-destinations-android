package com.pedroapps.californiatrips

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pedroapps.californiatrips.database.Destination
import com.pedroapps.californiatrips.database.DestinationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = DestinationsRepository(application)

    private val _appState = MutableStateFlow(DestinationsAppState())
    val appState = _appState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllDestinations()
                .distinctUntilChanged()
                .collect { destinationsList ->
                    updateDestinationsList(destinationsList)
                }
        }
    }

    //STATE FUNCTIONS

    private fun updateDestinationsList(destinations: List<Destination>?) {
        destinations?.let {
            _appState.update { currentState ->
                currentState.copy(destinations = it)
            }
        }
    }

    private fun updateCurrentDestination(destination: Destination) {
        _appState.update { currentState ->
            currentState.copy(currentDestination = destination)
        }
    }


    //DATABASE FUNCTIONS

    fun createNewDestination(destination: Destination) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNewDestination(destination)
        }
    }

}