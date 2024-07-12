import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.Date

@Composable
@Preview
fun App() {
    //println("App is running now")
    var text by remember { mutableStateOf("Hello, World!") }
    val shipments = remember { mutableStateListOf<TrackerViewHelper>() }


    var text1 by remember { mutableStateOf("") }
    Column {
        Row {
            OutlinedTextField(
                value = text1,
                onValueChange = { text1 = it },
                label = { Text("Label") }
            )
            Button(onClick = {
                text = "Track Shipment"
                val helper = TrackerViewHelper()
                helper.trackShipment(text1)
                shipments.add(helper)
            }) {
                Text("Track Shipment")
            }
        }
        Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
            shipments.forEach { obj ->
                DeletableCard(
                    trackerViewHelper = obj,
                    onDelete = {
                        obj.stopTracking()
                        shipments.remove(obj)

                    }
                )


            }
        }
    }
}
@Composable
fun DeletableCard(trackerViewHelper: TrackerViewHelper, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = trackerViewHelper.shipmentId,
                    modifier = Modifier.padding(16.dp)
                )

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Delete")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Status: ${trackerViewHelper.shipmentStatus}")
            Text("Location: ${trackerViewHelper.location}")
            Text("Expected Delivery Date: ${Date(trackerViewHelper.expectedShipmentDeliveryDate.toLong())}")
            Text("Notes: ${trackerViewHelper.shipmentTotes}")
            Text("Updates: ${trackerViewHelper.shipmentUpdateHistory}")
        }
    }
}

fun main() = runBlocking {
    async {TrackingSimulator.runSimulation()}

    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}
