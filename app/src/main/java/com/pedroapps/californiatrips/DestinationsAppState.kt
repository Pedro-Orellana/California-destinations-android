package com.pedroapps.californiatrips

import com.pedroapps.californiatrips.database.Destination

data class DestinationsAppState(
   var destinations: List<Destination>? = null,
   var currentDestination: Destination? = null
)