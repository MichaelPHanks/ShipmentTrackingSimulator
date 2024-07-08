import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TrackingSimulator : Subject {

        private var shipments: MutableList<Shipment> = mutableListOf()

        private var observers: MutableList<Observer> = mutableListOf()

    override fun addSubscription(observer: Observer) {
        observers.add(observer)
    }

    override fun removeSubscription(observer: Observer) {
        println(observer)
        for (item in observers)
        {
            println(item)
        }
        observers.remove(observer)
        println("Removed subscription ${observers.size}")

    }

    override fun notifyObservers(shipment: Shipment) {
        println(observers.size)
        for (observer in observers)
        {
            println("Notifying observer...")
            observer.update(shipment)
        }
    }


    fun findShipment(id: String): Shipment?
    {
        return shipments.find { it.getId() == id }
    }

    fun addShipment(shipment: Shipment)
    {
        this.shipments.add(shipment)
    }

    suspend fun runSimulation()
    {
        var shipment1: Shipment = Shipment("1", "New Fr", mutableListOf("1"), mutableListOf(), 15, "Salt lake City")


        shipments.add(shipment1)


        while (true)
        {
            shipments[0].setExpectedDeliveryDateTimeStamp(shipments[0].getExpectedDeliveryDateTimeStamp() + 1)
            notifyObservers(shipments[0])
            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
            }
            println("Updated....")
        }
    }


}