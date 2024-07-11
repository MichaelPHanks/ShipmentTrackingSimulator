import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import java.util.*

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


    fun stopTracking() {

        TrackingSimulator.findShipment(shipmentId)?.removeSubscription(this)

        //TrackingSimulator.removeSubscription(this)
    }


    fun trackShipment(id: String)
    {
        // Call the thing to listen for shipments
        //Simulation.registerTracker(id, this)

       // TrackingSimulator.addSubscription(this)
        val shipment: Shipment? = TrackingSimulator.findShipment(id)

        if (shipment != null)
        {
            shipment.addSubscription(this)
            this.setCard(shipment)
        }
    }

    private fun setCard(shipment: Shipment)
    {
        this.shipmentId = shipment.getId()
        this.shipmentUpdateHistory.clear()
        this.shipmentTotes.clear()
        for (item in shipment.getUpdateHistory())
        {
            this.shipmentUpdateHistory.add("Shipment went from ${item.getPreviousStatus()} to ${item.getNewStatus()} at ${Date(item.getTimeStamp().toLong())}." )
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

        if (shipment.getId() == this.shipmentId)
        {
            this.setCard(shipment)
        }

    }

}

