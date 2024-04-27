package com.pedroapps.californiatrips.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedroapps.californiatrips.database.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationDetailsCard(
    destination: Destination
) {

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    val hasVisitedText = if (destination.hasVisited) {
        "I have been here before"
    } else {
        "I will visit one day!"
    }

    val hasVisitedIcon = if(destination.hasVisited) {
        Icons.Filled.Check
    } else {
        Icons.Filled.Clear
    }

    val iconColor = if(destination.hasVisited) Color.Green else Color.Red



    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
    ) {
        Text(
            text = destination.name,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Text(
            text = destination.description,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 24.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)

        ) {
            Text(text = hasVisitedText)
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(
                imageVector = hasVisitedIcon,
                contentDescription = "visited icon",
                tint = iconColor)
        }


        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 20.dp, bottom = 12.dp)

        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Edit")
            }

            Button(onClick = { showDeleteDialog = true }) {
              Text(text = "Delete")
            }
        }


    }
    if(showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            confirmButton = { /*TODO*/ },
            title = { Text(text = "Delete this destination?")},
            text = { Text(text = "This action cannot be undone")}
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DestinationDetailsCardPreview() {
    val testDestination = Destination(
        id = 1010,
        name = "Sawyer Camp Trail",
        description = "The most beautiful bike trail I've found in the bay area so far",
        hasVisited = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DestinationDetailsCard(destination = testDestination)
    }

}