class Shipped: Update {
    override fun performUpdate(arguments: List<String>) {

        val shipment: Shipment? = TrackingSimulator.findShipment(arguments[1])

        if (shipment != null)
        {
            shipment.setStatus("shipped")
        }
        else
        {
            throw IllegalArgumentException("Trying to ship a non-existing shipment")
        }
    }
}