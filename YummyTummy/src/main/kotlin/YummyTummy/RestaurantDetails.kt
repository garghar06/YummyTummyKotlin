package YummyTummy
import java.util.Date
import java.text.SimpleDateFormat
import java.io.File

class RestaurantDetails(val name: String, val address: String, val phoneNumber: String, val rating: Double) {
    companion object  {
        val restaurantName = "Alpha Restaurant"
        val restaurantAddress = "123 Main St"
        val restaurantPhoneNumber = "555-123-4567"
        val restaurantRating = 4.5
    }

    fun dineIn(orderId: Int, customer: Customer, orderedItems: List<MenuItem>) {
        val orderedItems = orderedItems.map { it }

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val formattedDate = dateFormat.format(currentDate)

        val customerType = when (customer) {
            is RegularCustomer -> "Regular"
            is PremiumCustomer -> "Premium"
            is GuestCustomer -> "Guest"
            else -> "Unknown"
        }

        val fileName = "dineIn_${customerType}.txt"
        val file = File(fileName)

        file.writeText("Dining Order Details:\n")
        file.appendText("Order ID: $orderId\n")
        file.appendText("Customer: ${customer.getName().firstName} ${customer.getName().lastName}\n")
        file.appendText("Order Date and Time: $formattedDate\n")
        file.appendText("Items:\n")

        for ((index, item) in orderedItems.withIndex()) {
            file.appendText("${index + 1}. ${item.name} - $${item.price}\n")
        }

        val totalBill = orderedItems.sumByDouble { it.price }*0.3
        file.appendText("Total Bill: rupee $totalBill\n")

        println("Dining Order Details saved in file: $fileName")

        val data = file.readText()
        println(data)


    }

}
