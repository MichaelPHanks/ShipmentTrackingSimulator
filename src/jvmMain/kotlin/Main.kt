import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.reflect.KProperty

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val shipments = remember { mutableStateListOf<TrackerViewHelper>() }
//    MaterialTheme {
//        Button(onClick = {
//            text = "Hello, Desktop!"
//            shipments.add(TrackerViewHelper())
//        }) {
//            Text(text)
//        }
//
//    }
    Column {
        Button(onClick = {
            text = shipments.count().toString()
            val helper = TrackerViewHelper()
            helper.trackShipment("12")
            shipments.add(TrackerViewHelper())
        }) {
            Text(text)
        }

        Column {

            shipments.forEach { obj ->
                Button(onClick = {})
                {
//                    Text("Name: ${obj.name}, Value: ${obj.value}")
                }

            }
        }

    }
}



fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
