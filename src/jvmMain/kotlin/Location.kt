class Location: Update {
    override fun performUpdate(arguments: List<String>) {
        val shipment: Shipment? = TrackingSimulator.findShipment(arguments[1])

        if (shipment != null && validator(arguments))
        {
            shipment.setCurrentLocation(arguments[3])
        }
        else
        {
            throw IllegalArgumentException("Trying to set a location to a non-existent shipment")
        }
    }

    override fun validator(arguments: List<String>): Boolean {
        return arguments.size == 4
    }
}