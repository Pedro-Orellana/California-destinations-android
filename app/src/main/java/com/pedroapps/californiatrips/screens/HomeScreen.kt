package com.pedroapps.californiatrips.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedroapps.californiatrips.components.DestinationCard
import com.pedroapps.californiatrips.components.SwipeToDeleteContainer
import com.pedroapps.californiatrips.database.Destination

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    destinations: List<Destination>?,
    cardClickHandler: (destinationID: Int) -> Unit,
    addDestinationClickHandler: () -> Unit,
    deleteDestination: (Destination) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Home Screen",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp)
            )

            destinations?.let { destinationsList ->
                LazyColumn {
                    items(
                        count = destinationsList.size,
                        key = { destinationsList[it].id }
                    ) { index ->
                        val currentDestination = destinationsList[index]
                        SwipeToDeleteContainer(
                            item = currentDestination,
                            onDelete = deleteDestination
                        ) {
                            DestinationCard(
                                destination = it,
                                clickHandler = cardClickHandler
                            )
                        }
                    }
                }
            }
        }


        FloatingActionButton(
            onClick = addDestinationClickHandler,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
            ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "add Icon")
        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val paddingValues = PaddingValues()
    HomeScreen(
        paddingValues = paddingValues,
        destinations = null,
        cardClickHandler = {},
        addDestinationClickHandler = {},
        deleteDestination = {}
    )
}
