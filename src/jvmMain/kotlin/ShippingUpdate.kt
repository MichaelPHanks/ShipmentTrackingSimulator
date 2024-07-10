class ShippingUpdate(
    private var previousStatus: String,
    private var newStatus: String,
    private var timeStamp: Long) {

    fun getPreviousStatus(): String
    {
        return this.previousStatus
    }

    fun setPreviousStatus(prevStatus: String)
    {
        this.previousStatus = prevStatus

    }

    fun getNewStatus(): String
    {
        return this.newStatus
    }

    fun setNewStatus(newStatus: String)
    {
        this.newStatus = newStatus
    }

    fun getTimeStamp(): Long
    {
        return this.timeStamp
    }

    fun setTimeStamp(timeStamp: Long)
    {
        this.timeStamp = timeStamp
    }
}