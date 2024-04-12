package com.pedroapps.californiatrips.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pedroapps.californiatrips.database.Destination

@Composable
fun NewDestinationScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    createNewDestination: (Destination) -> Unit

) {
    val destinationName = remember {
        mutableStateOf("")
    }

    val destinationDescription = remember {
        mutableStateOf("")
    }

    val hasVisited = remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Text(
            text = "Create a new destination",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
        )

        OutlinedTextField(
            value = destinationName.value,
            onValueChange = { destinationName.value = it },
            shape = RoundedCornerShape(20),
            label = { Text(text = "Destination name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 20.dp)
        )


        OutlinedTextField(
            value = destinationDescription.value,
            onValueChange = { destinationDescription.value = it },
            label = { Text(text = "Description") },
            minLines = 3,
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 20.dp)
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .selectableGroup()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = false,
                        enabled = true,
                        role = Role.RadioButton,
                        onClick = { hasVisited.value = true }
                    )
            ) {
                RadioButton(selected = hasVisited.value, onClick = null)
                Text(text = "I have visited this place!")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = false,
                        enabled = true,
                        role = Role.RadioButton,
                        onClick = { hasVisited.value = false }
                    )
            ) {
                RadioButton(selected = !hasVisited.value, onClick = null)
                Text(text = "I will visit in the future!")
            }
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Button(
                onClick = {
                    val destination = createDestination(
                        destinationName = destinationName.value,
                        destinationDescription = destinationDescription.value,
                        hasVisited = hasVisited.value
                    )
                    createNewDestination(destination)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(end = 20.dp)
            ) {
                Text(text = "Save")
            }

            TextButton(onClick = { navController.popBackStack() }) {
                Text(text = "Cancel")
            }
        }

    }

}


private fun createDestination(
    destinationName: String,
    destinationDescription: String,
    hasVisited: Boolean
): Destination {
    return Destination(
        name = destinationName,
        description = destinationDescription,
        hasVisited = hasVisited
    )
}

@Preview(showBackground = true)
@Composable
fun NewDestinationScreenPreview() {
    val paddingValues = PaddingValues()
    val navController = rememberNavController()
    NewDestinationScreen(
        paddingValues = paddingValues,
        navController = navController,
        createNewDestination = {}
    )
}