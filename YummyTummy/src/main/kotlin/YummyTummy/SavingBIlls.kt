package YummyTummy

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun saveBillingDetails(order: Order, totalBill: Double) {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    val formattedDate = dateFormat.format(currentDate)

    val customerType = when (order.customer) {
        is RegularCustomer -> "Regular"
        is PremiumCustomer -> "Premium"
        is GuestCustomer -> "Guest"
        else -> "Unknown"
    }

    val fileName = "order_${customerType}.txt"
    val file = File(fileName)

    file.writeText("Order ID: ${order.orderId}\n")
    file.appendText("Customer: ${order.customer.getName().firstName} ${order.customer.getName().lastName}\n")
    file.appendText("Order Date and Time: ${formattedDate}\n")
    file.appendText("Items:\n")

    for ((index, item) in order.orderedItems.withIndex()) {
        file.appendText("${index + 1}. ${item.name} - $${item.price}\n")
    }

    file.appendText("Total Bill after all privileges : rupee $totalBill\n")
    val data = file.readText()
    println(data)
}
