import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.mutableStateOf

class TrackerViewHelper(
    ) {
//    var shipmentId by mutableStateOf(shipment.id)
    init {

    }

    var shipmentId by mutableStateOf("")
        private set
    var shipmentTotes by mutableStateOf(arrayOf<String>())
        private set
    var shipmentUpdateHistory by mutableStateOf(arrayOf<String>())
        private set
    var expectedShipmentDeliveryDate by mutableStateOf(arrayOf<String>())
        private set
    var shipmentStatus by mutableStateOf("")
        private set



    fun update(shipment: Shipment)
    {
        this.shipmentId = shipment.id
    }

    fun trackShipment(id: String)
    {
        // Call the thing to listen for shipments
        Simulation.registerTracker(id, this)
    }

}

