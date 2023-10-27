package YummyTummy
import java.util.Date
import java.text.SimpleDateFormat

class Order(private var orderId: Int, val customer: Customer, private var orderedItems: List<MenuItem>) {
    fun calculateBill(): Double {
        return orderedItems.sumByDouble { it.price }
    }

    fun displayOrderDetails() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val formattedDate = dateFormat.format(currentDate)
        println("YummyTummy.Order Details:")
        println("YummyTummy.Order ID: $orderId")
        println("YummyTummy.Customer: ${customer.getName().firstName} ${customer.getName().lastName}")
        println("Order Date and Time: $formattedDate")
        println("Items:")
        orderedItems.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name} - rupee ${item.price}")
        }
        println("Total Bill: rupee ${calculateBill()}")
    }
}