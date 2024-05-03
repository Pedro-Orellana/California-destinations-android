package com.pedroapps.californiatrips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pedroapps.californiatrips.components.AppTopAppBar
import com.pedroapps.californiatrips.screens.DestinationDetailsScreen
import com.pedroapps.californiatrips.screens.HomeScreen
import com.pedroapps.californiatrips.screens.NewDestinationScreen
import com.pedroapps.californiatrips.screens.ScreenDestinations
import com.pedroapps.californiatrips.ui.theme.CaliforniaTripsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaliforniaTripsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContainer()
                }
            }
        }
    }
}


@Composable
fun MainContainer() {

    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val appState = viewModel.appState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppTopAppBar() },
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = ScreenDestinations.HomeScreenDestination
        ) {
            composable(route = ScreenDestinations.HomeScreenDestination) {
                HomeScreen(
                    paddingValues = paddingValues,
                    destinations = appState.value.destinations,
                    cardClickHandler = { destinationID ->
                        navController.navigate("${ScreenDestinations.DetailsScreenDestination}/${destinationID}")
                    },
                    addDestinationClickHandler = { navController.navigate(ScreenDestinations.NewDestinationScreenDestination) },
                    deleteDestination = viewModel::deleteDestination
                )
            }

            composable(route = ScreenDestinations.NewDestinationScreenDestination) {
                NewDestinationScreen(
                    paddingValues = paddingValues,
                    navController = navController,
                    createNewDestination = { destination ->
                        viewModel.createNewDestination(
                            destination
                        )
                    }
                )
            }

            composable(
                route = "${ScreenDestinations.DetailsScreenDestination}/{destinationID}",
                arguments = listOf(navArgument("destinationID") { type = NavType.IntType })
            ) {
                DestinationDetailsScreen(
                    paddingValues = paddingValues,
                    navController = navController,
                    destinationID = it.arguments?.getInt("destinationID") ?: 0,
                    getDestinationByIDFlow = viewModel::getDestinationByIDFlow,
                    deleteDestination = viewModel::deleteDestination,
                    updateDestination = viewModel::updateDestination
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContainerPreview() {
    MainContainer()
}