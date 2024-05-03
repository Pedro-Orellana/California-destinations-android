package com.pedroapps.californiatrips.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pedroapps.californiatrips.components.DestinationDetailsCard
import com.pedroapps.californiatrips.database.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@Composable
fun DestinationDetailsScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    destinationID: Int,
    getDestinationByIDFlow: (Int) -> Flow<Destination>,
    deleteDestination: (destination: Destination) -> Unit,
    updateDestination: (destination: Destination) -> Unit
) {

    val testDestination = Destination(
        id = 1000,
        name = "San Jose",
        description = "A beautiful city with many things to see and do",
        hasVisited = true
    )


    val destinationState = getDestinationByIDFlow(destinationID).collectAsStateWithLifecycle(
        initialValue = testDestination
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        DestinationDetailsCard(
            destination = destinationState.value,
            deleteDestination = deleteDestination,
            updateDestination = updateDestination,
            navController = navController
        )
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
        navController = rememberNavController(),
        destinationID = testDestination.id,
        getDestinationByIDFlow = { flow { } },
        deleteDestination = {},
        updateDestination = {}
    )
}