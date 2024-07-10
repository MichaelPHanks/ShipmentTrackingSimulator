class Create: Update {
    override fun performUpdate(arguments: List<String>) {
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

    override fun validator(arguments: List<String>): Boolean {
        TODO("Not yet implemented")
    }
}