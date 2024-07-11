class Shipment(
    private var id: String,
    private var status: String,
    private var notes: MutableList<String>,
    private var updateHistory: MutableList<ShippingUpdate>,
    private var expectedDeliveryDateTimeStamp: Long,
    private var currentLocation: String
) : Subject {
    private var observers: MutableList<Observer> = mutableListOf()



    override fun addSubscription(observer: Observer) {
        observers.add(observer)
    }

    override fun removeSubscription(observer: Observer) {
//        println(observer)
//        for (item in observers)
//        {
//            println(item)
//        }
        observers.remove(observer)
       // println("Removed subscription ${observers.size}")

    }

    override fun notifyObservers(shipment: Shipment) {
        //println(observers.size)
        for (observer in observers)
        {
           // println("Notifying observer...")
            observer.update(shipment)
        }
    }


    fun addNote(note: String) {
        this.notes.add(note)
    }

    fun addUpdate(update: ShippingUpdate) {
        this.updateHistory.add(update)
        notifyObservers(this)
    }

    fun getStatus(): String {
        return this.status
    }

    fun setStatus(status: String) {
        this.status = status
        notifyObservers(this)

    }

    fun getId(): String {
        return this.id
    }

//    fun setId(id: String) {
//        this.id = id
//    }

    fun getNotes(): MutableList<String> {
        return this.notes
    }


    fun getUpdateHistory(): MutableList<ShippingUpdate> {
        return this.updateHistory
    }


    fun getExpectedDeliveryDateTimeStamp(): Long {
        return this.expectedDeliveryDateTimeStamp
    }

    fun setExpectedDeliveryDateTimeStamp(expectedDeliveryDateTimeStamp: Long) {
        this.expectedDeliveryDateTimeStamp = expectedDeliveryDateTimeStamp
        notifyObservers(this)

    }

    fun getCurrentLocation(): String {
        return this.currentLocation
    }

    fun setCurrentLocation(currentLocation: String) {
        this.currentLocation = currentLocation
        notifyObservers(this)

    }
}
