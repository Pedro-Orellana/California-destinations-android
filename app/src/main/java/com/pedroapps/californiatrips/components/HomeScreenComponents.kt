package com.pedroapps.californiatrips.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedroapps.californiatrips.database.Destination

@Composable
fun DestinationCard(destination: Destination) {
    ElevatedCard(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 50.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
        
    ) {
        Text(text = destination.name)
        Text(text = destination.description)

    }

}

@Preview(showBackground = true)
@Composable
fun DestinationCardPreview() {
    val testDestination = Destination(
        id = 1,
        name = "San Francisco",
        description = "really big city",
        hasVisited = true
    )
    DestinationCard(testDestination)
}