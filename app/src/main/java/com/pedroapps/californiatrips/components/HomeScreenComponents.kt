package com.pedroapps.californiatrips.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedroapps.californiatrips.database.Destination

@Composable
fun DestinationCard(
    destination: Destination,
    clickHandler: (destinationName: String) -> Unit,
) {
    ElevatedCard(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 50.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { clickHandler(destination.name) }

    ) {
        Text(
            text = destination.name,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(4.dp)
        )

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
    DestinationCard(
        testDestination,
        clickHandler = {}
    )
}