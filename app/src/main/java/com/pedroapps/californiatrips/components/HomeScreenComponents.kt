package com.pedroapps.californiatrips.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedroapps.californiatrips.database.Destination
import kotlinx.coroutines.delay

@Composable
fun DestinationCard(
    destination: Destination,
    clickHandler: (destinationID: Int) -> Unit,
) {
    ElevatedCard(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 50.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { clickHandler(destination.id) }

    ) {
        Text(
            text = destination.name,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(4.dp)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart)
        Color.Red else Color.Transparent

    Box(
        contentAlignment = Alignment.CenterEnd,

        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(color)

    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "delete icon",
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer (
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit

) {

    var isRemoved by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    val dismissState = rememberDismissState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically (
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {

        SwipeToDismiss(
            state = dismissState,
            background = { DeleteBackground(swipeDismissState = dismissState) },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart))

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