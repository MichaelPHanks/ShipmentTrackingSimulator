import org.jetbrains.annotations.TestOnly
import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class TestShipmentUpdate {


    @Test
    fun TestCreateShipment()
    {

    }
    @Test
    fun TestShippedShipment()
    {

    }
    @Test
    fun TestLostShipment()
    {

    }
    @Test
    fun TestLocationShipment()
    {

    }

    @Test
    fun TestShipmentNotExisting()
    {

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