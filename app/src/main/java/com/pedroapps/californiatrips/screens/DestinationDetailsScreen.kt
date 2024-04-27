package com.pedroapps.californiatrips.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedroapps.californiatrips.components.DestinationDetailsCard
import com.pedroapps.californiatrips.database.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@Composable
fun DestinationDetailsScreen(
    paddingValues: PaddingValues,
    getDestinationByNameFlow: (name: String) -> Flow<Destination>,
    destinationName: String
) {

    val testDestination = Destination(
        id = 1000,
        name = "San Jose",
        description = "A beautiful city with many things to see and do",
        hasVisited = true
    )
    
    val destinationState = getDestinationByNameFlow(destinationName).collectAsStateWithLifecycle(
        initialValue = testDestination
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
//        Text(text = "Details Screen")
//        Text(text = "current destination: ${destinationState.value.name}")
//        Text(text = destinationState.value.description)
//        Text(text = "Has visited: ${destinationState.value.hasVisited}")

        DestinationDetailsCard(destination = destinationState.value)
    }



}


@Composable
@Preview(showBackground = true)

fun DestinationDetailsScreenPreview() {
    val paddingValues = PaddingValues()
    val testDestination = Destination(
        id = 1000,
        name = "San Jose",
        description = "A beautiful city with many things to see and do",
        hasVisited = true
    )

    DestinationDetailsScreen(
        paddingValues = paddingValues,
        getDestinationByNameFlow = { flow { }},
        destinationName = testDestination.name
    )
}