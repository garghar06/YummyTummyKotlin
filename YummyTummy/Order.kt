package YummyTummy
import java.util.Date
import java.text.SimpleDateFormat

class Order( var orderId: Int, val customer: Customer,  var orderedItems: List<MenuItem>) {
    val calculateBill: () -> Double = {
        orderedItems.sumByDouble { it.price }
    }

    fun displayOrderDetails() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val formattedDate = dateFormat.format(currentDate)
        println("------------YummyTummy.Order Details:-------------")
        orderedItems.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name} - rupee ${item.price}")
        }
        println("Total Bill: rupee ${calculateBill()}")
    }
}