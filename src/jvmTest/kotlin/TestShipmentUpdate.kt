import org.jetbrains.annotations.TestOnly
import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class TestShipmentUpdate {


    @Test
    fun TestCreateShipment()
    {
        val create = Create()
        val arguments: MutableList<String> = mutableListOf("created", "newShipment", "12345678")

        create.performUpdate(arguments)

        val shipment: Shipment? = TrackingSimulator.findShipment("newShipment")

        assertNotNull(shipment)
        assertEquals("created", shipment.getStatus())
        assertEquals(0, shipment.getUpdateHistory().size)
        assertEquals(-1, shipment.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment.getNotes().size)

    }
    @Test
    fun TestShippedShipment()
    {
        val shipment = Shipment("98", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val shipped = Shipped()
        val arguments: MutableList<String> = mutableListOf("shipped", "98", "12345678", "12345678")

        shipped.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("98")

        assertNotNull(shipment1)
        assertEquals("shipped", shipment1.getStatus())
        assertEquals(1, shipment1.getUpdateHistory().size)
        assertEquals(12345678, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment1.getNotes().size)

    }
    @Test
    fun TestLostShipment()
    {
        val shipment = Shipment("99", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val lost = Lost()
        val arguments: MutableList<String> = mutableListOf("lost", "99", "12345678")

        lost.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("99")

        assertNotNull(shipment1)
        assertEquals("lost", shipment1.getStatus())
        assertEquals(1, shipment1.getUpdateHistory().size)
        assertEquals(-1, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment1.getNotes().size)

    }
    @Test
    fun TestLocationShipment()
    {
        val shipment = Shipment("100", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val location = Location()
        val arguments: MutableList<String> = mutableListOf("location", "100", "12345678", "Salt Lake City, UT")

        location.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("100")

        assertNotNull(shipment1)
        assertEquals("created", shipment1.getStatus())
        assertEquals(0, shipment1.getUpdateHistory().size)
        assertEquals(-1, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment1.getNotes().size)

    }

    @Test
    fun TestAddingNote()
    {
        val shipment = Shipment("101", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val note = NoteAdded()
        val arguments: MutableList<String> = mutableListOf("location", "101", "12345678", "something happened to the package!!!")

        note.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("101")

        assertNotNull(shipment1)
        assertEquals("created", shipment1.getStatus())
        assertEquals(0, shipment1.getUpdateHistory().size)
        assertEquals(-1, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(1, shipment1.getNotes().size)
    }

    @Test
    fun TestDeliveringShipment()
    {
        val shipment = Shipment("102", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val delivery = Delivered()
        val arguments: MutableList<String> = mutableListOf("delivered", "102", "12345678")

        delivery.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("102")

        assertNotNull(shipment1)
        assertEquals("delivered", shipment1.getStatus())
        assertEquals(1, shipment1.getUpdateHistory().size)
        assertEquals(-1, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment1.getNotes().size)
    }

    @Test
    fun TestCancelingShipment()
    {
        val shipment = Shipment("103", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val cancel = Canceled()
        val arguments: MutableList<String> = mutableListOf("canceled", "103", "12345678")

        cancel.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("103")

        assertNotNull(shipment1)
        assertEquals("canceled", shipment1.getStatus())
        assertEquals(1, shipment1.getUpdateHistory().size)
        assertEquals(-1, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment1.getNotes().size)
    }

    @Test
    fun TestDelayingShipment()
    {
        val shipment = Shipment("104", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val delay = Delayed()
        val arguments: MutableList<String> = mutableListOf("delayed", "104", "12345678", "12345679")

        delay.performUpdate(arguments)

        val shipment1: Shipment? = TrackingSimulator.findShipment("104")

        assertNotNull(shipment1)
        assertEquals("delayed", shipment1.getStatus())
        assertEquals(1, shipment1.getUpdateHistory().size)
        assertEquals(12345679, shipment1.getExpectedDeliveryDateTimeStamp())
        assertEquals(0, shipment1.getNotes().size)
    }



    @Test
    fun TestFailingLongCheck()
    {
        val shipment = Shipment("1234", "created", mutableListOf(),  mutableListOf(), -1, "unknown")
        val arguments: MutableList<String> = mutableListOf("NA", "1234")
        val create = Create()
        val delivered = Delivered()
        val lost = Lost()
        val delayed = Delayed()
        val location = Location()
        val canceled = Canceled()
        val noteAdded = NoteAdded()
        val shipped = Shipped()
        TrackingSimulator.addShipment(shipment)
        arguments.add("hello!")

        assertFailsWith<IllegalArgumentException> {delivered.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {lost.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {canceled.performUpdate(arguments.toList())}

        arguments.add("hello!")

        assertFailsWith<IllegalArgumentException> {location.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {canceled.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {noteAdded.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {shipped.performUpdate(arguments.toList())}

        arguments.removeLast()
        arguments.removeLast()
        arguments.add("-1")
        arguments.add("hello again!")
        assertFailsWith<IllegalArgumentException> {create.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {delivered.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {lost.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {delayed.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {canceled.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {shipped.performUpdate(arguments.toList())}

    }

    @Test
    fun TestIncorrectNumberOfArguments()
    {

        val shipment = Shipment("1234", "created", mutableListOf(),  mutableListOf(), -1, "unknown")
        val arguments: MutableList<String> = mutableListOf("NA", "1234")

        TrackingSimulator.addShipment(shipment)
        val create = Create()
        val delivered = Delivered()
        val lost = Lost()
        val delayed = Delayed()
        val location = Location()
        val canceled = Canceled()
        val noteAdded = NoteAdded()
        val shipped = Shipped()

        // To fail with 2
        assertFailsWith<IllegalArgumentException> {create.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {delivered.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {lost.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {canceled.performUpdate(arguments.toList())}




        // To fail with 3
        arguments.add("hello!")
        assertFailsWith<IllegalArgumentException> {create.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {location.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {delayed.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {noteAdded.performUpdate(arguments.toList())}
        assertFailsWith<IllegalArgumentException> {shipped.performUpdate(arguments.toList())}


    }
    @Test
    fun TestShipmentExistingOrNot()
    {
        val shipment = Shipment("1234", "created", mutableListOf(),  mutableListOf(), -1, "unknown")

        TrackingSimulator.addShipment(shipment)
        val create = Create()
        val delivered = Delivered()
        val lost = Lost()
        val delayed = Delayed()
        val location = Location()
        val canceled = Canceled()
        val noteAdded = NoteAdded()
        val shipped = Shipped()
        // Shipment does not exist for create
        val arguments1: MutableList<String> = mutableListOf("NA", "12345", "hello....")
        assertFailsWith<IllegalArgumentException> {create.performUpdate(arguments1.toList())}

        // Attempting to update non-existing shipment

        arguments1.removeLast()
        arguments1.add("1234")
        // With 3 arguments
        assertFailsWith<IllegalArgumentException> {delivered.performUpdate(arguments1.toList())}
        assertFailsWith<IllegalArgumentException> {lost.performUpdate(arguments1.toList())}
        assertFailsWith<IllegalArgumentException> {canceled.performUpdate(arguments1.toList())}

        arguments1.add(":)")
        // With 4 arguments
        assertFailsWith<IllegalArgumentException> {delayed.performUpdate(arguments1.toList())}
        assertFailsWith<IllegalArgumentException> {shipped.performUpdate(arguments1.toList())}
        assertFailsWith<IllegalArgumentException> {noteAdded.performUpdate(arguments1.toList())}
        assertFailsWith<IllegalArgumentException> {location.performUpdate(arguments1.toList())}

    }

}