import kotlin.test.Test
import kotlin.test.assertEquals

class TestObserverPattern {
    // NOTE: Cannot run suspend function in kotlin tests

    @Test
    fun testSomething()
    {

        TrackingSimulator.findShipment("1")
    }

    @Test
    fun testTrackerViewHelperUpdated()
    {
        var shipment = Shipment("1234", "created", mutableListOf(),  mutableListOf(), -1, "unknown")
        val tracker = TrackerViewHelper()
        TrackingSimulator.addShipment(shipment)
        tracker.trackShipment("1234")
        assertEquals(tracker.shipmentStatus, "created")
        shipment.setStatus("shipped")

        shipment.addUpdate(ShippingUpdate(shipment.getStatus(), "shipped", "1652712855468".toLong()))
        assertEquals(tracker.shipmentStatus, "shipped")




    }


}