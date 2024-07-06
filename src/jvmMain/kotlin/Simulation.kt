object Simulation {

    var observers = mutableMapOf<String, TrackerViewHelper>()
    var shipments = mutableListOf<Shipment>()
    fun registerTracker(id: String, observer: TrackerViewHelper)
    {
        observers.put(id, observer)
    }

    fun simulator()
    {

    }

}