class Shipment(
    private var id: String,
    private var status: String,
    private var notes: MutableList<String>,
    private var updateHistory: MutableList<ShippingUpdate>,
    private var expectedDeliveryDateTimeStamp: Long,
    private var currentLocation: String
) {

    fun addNote(note: String) {
        this.notes.add(note)
    }

    fun addUpdate(update: ShippingUpdate) {
        this.updateHistory.add(update)
    }

    fun getStatus(): String {
        return this.status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getId(): String {
        return this.id
    }

    fun setId(id: String) {
        this.id = id
    }

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
    }

    fun getCurrentLocation(): String {
        return this.currentLocation
    }

    fun setCurrentLocation(currentLocation: String) {
        this.currentLocation = currentLocation
    }
}
