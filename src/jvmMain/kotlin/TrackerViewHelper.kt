import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
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
import kotlin.reflect.KProperty
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class TrackerViewHelper : Observer {
//    var shipmentId by mutableStateOf(shipment.id)
    init {

    }

    var shipmentId by mutableStateOf("")
        private set
    var shipmentTotes by mutableStateOf(mutableListOf<String>())
        private set
    var shipmentUpdateHistory by mutableStateOf(mutableListOf<String>())
        private set
    var expectedShipmentDeliveryDate by mutableStateOf("")
        private set
    var shipmentStatus by mutableStateOf("")
        private set

    var location by mutableStateOf("")



    fun stopTracking()
    {
        println("Stopped tracking...")
        println(this)
        TrackingSimulator.removeSubscription(this)
    }


    fun trackShipment(id: String)
    {
        // Call the thing to listen for shipments
        //Simulation.registerTracker(id, this)

        println(this)
        TrackingSimulator.addSubscription(this)
        val shipment: Shipment? = TrackingSimulator.findShipment(id)

        if (shipment != null)
        {
            println("Found shipment")
            this.setCard(shipment)
        }
    }

    private fun setCard(shipment: Shipment)
    {
        println("Setting card information")
        this.shipmentId = shipment.getId()
        this.shipmentUpdateHistory.clear()
        this.shipmentTotes.clear()
        for (item in shipment.getUpdateHistory())
        {
            this.shipmentUpdateHistory.add("Shipment went from ${item.getPreviousStatus()} to ${item.getNewStatus()} at ${item.getTimeStamp()}." )
        }

        for (item in shipment.getNotes())
        {
            this.shipmentTotes.add(item)
        }

        this.shipmentStatus = shipment.getStatus()
        this.location = shipment.getCurrentLocation()
        this.expectedShipmentDeliveryDate = shipment.getExpectedDeliveryDateTimeStamp().toString()
    }

    override fun update(shipment: Shipment) {
        println("recieved new update!")

        if (shipment.getId() == this.shipmentId)
        {
            this.setCard(shipment)
            println("recieved new update!")

        }

    }

}

