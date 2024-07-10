class Shipped: Update {
    override fun performUpdate(arguments: List<String>) {


        val shipment: Shipment? = TrackingSimulator.findShipment(arguments[1])

        if (shipment != null)
        {
            val time = arguments[2].toLongOrNull()

            if (time != null) {

                val shippingUpdate = ShippingUpdate(shipment.getStatus(), "shipped", arguments[2].toLong())
                shipment.setStatus("shipped")
                shipment.setExpectedDeliveryDateTimeStamp(arguments[2].toLong())
                shipment.addUpdate(shippingUpdate)
            }
            else
            {
                throw IllegalArgumentException("Could not parse time stamp")
            }
        }
        else
        {
            throw IllegalArgumentException("Trying to ship a non-existing shipment")
        }
    }

    override fun validator(arguments: List<String>): Boolean {
        TODO("Not yet implemented")
    }
}