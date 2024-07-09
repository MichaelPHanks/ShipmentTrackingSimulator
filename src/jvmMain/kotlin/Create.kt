class Create: Update {
    override fun performUpdate(arguments: List<String>) {
        println("Creating shipment...")
        val shipment: Shipment? =  TrackingSimulator.findShipment(arguments[1])

        if (shipment == null)
        {
            TrackingSimulator.addShipment(Shipment(arguments[1], "Created", mutableListOf(),  mutableListOf(), -1, "unknown"))
        }
        else
        {
            throw IllegalArgumentException("Shipment already exists and cannot be created...")
        }
    }
}