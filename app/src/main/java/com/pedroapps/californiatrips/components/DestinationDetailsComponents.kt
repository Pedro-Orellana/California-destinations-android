package com.pedroapps.californiatrips.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pedroapps.californiatrips.database.Destination

@Composable
fun DestinationDetailsCard(
    destination: Destination,
    deleteDestination: (Destination) -> Unit,
    updateDestination: (Destination) -> Unit,
    navController: NavHostController
) {

    val (showDeleteDialog, setShowDialog) = remember {
        mutableStateOf(false)
    }

    val (editMode, setEditMode) = remember {
        mutableStateOf(false)
    }


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
    //TODO(The animation looks a little clunky here
        // Instead of creating an animation for the contents of the card
        // create an animation for the whole card)
        Crossfade(
            targetState = editMode,
            label = "crossfade effect for content"
        ) { isInEditMode ->
            if (isInEditMode) {
                EditModeContent(
                    destination = destination,
                    setEditMode = setEditMode
                )
            } else {
                CardContent(
                    destination = destination,
                    setShowDialog = setShowDialog,
                    setEditMode = setEditMode
                )
            }
        }


    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { setShowDialog(false) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    tint = Color.Red
                )
            },
            title = { Text(text = "Delete this destination?") },
            text = { Text(text = "This action cannot be undone") },
            confirmButton = {
                TextButton(onClick = {
                    setShowDialog(false)
                    deleteDestination(destination)
                    navController.popBackStack()
                }) {
                    Text(
                        text = "Delete",
                        color = Color.Red
                    )

                }
            },
            dismissButton = {
                Button(onClick = { setShowDialog(false) }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

}


@Composable
private fun CardContent(
    destination: Destination,
    setShowDialog: (Boolean) -> Unit,
    setEditMode: (Boolean) -> Unit
) {

    val hasVisitedText = if (destination.hasVisited) {
        "I have been here before"
    } else {
        "I will visit one day!"
    }

    val hasVisitedIcon = if (destination.hasVisited) {
        Icons.Filled.Check
    } else {
        Icons.Filled.Clear
    }

    val iconColor = if (destination.hasVisited) Color.Green else Color.Red


    Column {

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
                tint = iconColor
            )
        }


        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 20.dp, bottom = 12.dp)

        ) {
            TextButton(onClick = { setEditMode(true) }) {
                Text(text = "Edit")
            }

            Button(onClick = { setShowDialog(true) }) {
                Text(text = "Delete")
            }
        }

    }

}

@Composable
private fun EditModeContent(
    destination: Destination,
    setEditMode: (Boolean) -> Unit
) {

    val updatedDestination = remember {
        mutableStateOf(destination)
    }

    Column {
        TextField(
            value = updatedDestination.value.name,
            onValueChange = { updatedDestination.value.name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 20.dp)
        )

        TextField(
            value = updatedDestination.value.description,
            onValueChange = { updatedDestination.value.description = it },
            minLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 20.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
                .padding(top = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = updatedDestination.value.hasVisited,
                        role = Role.RadioButton,
                        enabled = true,
                        onClick = { updatedDestination.value.hasVisited = true }
                    )
                    .padding(bottom = 12.dp)
            ) {
                RadioButton(selected = updatedDestination.value.hasVisited, onClick = null)
                Text(text = "I have been here")
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = !updatedDestination.value.hasVisited,
                        role = Role.RadioButton,
                        enabled = true,
                        onClick = { updatedDestination.value.hasVisited = false }
                    )
                    .padding(bottom = 12.dp)
            ) {
                RadioButton(selected = !updatedDestination.value.hasVisited, onClick = null)
                Text(text = "I have not been here")
            }
        }


        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp)
        ) {
            Button(onClick = { setEditMode(false) }) {
                Text(text = "Cancel")
            }

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Save")
            }
        }
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
        DestinationDetailsCard(
            destination = testDestination,
            deleteDestination = {},
            updateDestination = {},
            navController = rememberNavController()
        )
    }

}


@Preview(showBackground = true)
@Composable
fun EditModeContentPreview() {

    val testDestination = Destination(
        id = 1010,
        name = "Sawyer Camp Trail",
        description = "The most beautiful bike trail I've found in the bay area so far",
        hasVisited = true
    )

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
        EditModeContent(
            destination = testDestination,
            setEditMode = {})
    }

}