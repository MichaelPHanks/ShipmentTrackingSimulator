import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import kotlin.reflect.KProperty

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val shipments = remember { mutableStateMapOf<String, TrackerViewHelper>() }

//    MaterialTheme {
//        Button(onClick = {
//            text = "Hello, Desktop!"
//            shipments.add(TrackerViewHelper())
//        }) {
//            Text(text)
//        }
//
//    }
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
            shipments[text1] = helper
        }) {
            Text("Track Shipment")
        }
    }
    Column {
        shipments.forEach { obj ->
            DeletableCard(
                text = obj.value.expectedShipmentDeliveryDate.toString(),
                onDelete = {
                    obj.value.stopTracking()
                    shipments.remove(obj.key)

                }
            )


        }
    }
}
//    Column {
//        Button(onClick = {
//            text = shipments.count().toString()
//            val helper = TrackerViewHelper()
//            helper.trackShipment("12")
//            shipments.add(TrackerViewHelper())
//        }) {
//            Text(text)
//        }
//
//        Column {
//
//            shipments.forEach { obj ->
//                Button(onClick = {})
//                {
////                    Text("Name: ${obj.name}, Value: ${obj.value}")
//                }
//
//            }
//        }
//
//    }
}
@Composable
fun DeletableCard(text: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp)
            )
            IconButton(
                onClick = onDelete,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Delete")
            }
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
