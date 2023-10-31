package YummyTummy

open class Customer(private var customerId: Int, private var name: Name, private var address: Address, private var phoneNumber: String) {
    val orders = mutableListOf<Order>()

    fun placeOrder(orderId: Int, itemIndices: List<Int>) {
        val selectedItems = itemIndices.map { Menu.menuItems[it] }
        val order = Order(orderId, this, selectedItems)
        orders.add(order)
    }

    // Getters and setters for customer properties
    fun getCustomerId(): Int {
        return customerId
    }

    fun setCustomerId(customerId: Int) {
        this.customerId = customerId
    }

    fun getName(): Name {
        return name
    }

    fun setName(name: Name) {
        this.name = name
    }

    fun getAddress(): Address {
        return address
    }

    fun setAddress(address: Address) {
        this.address = address
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }
    open fun payBill(order: Order): Double {
        return order.calculateBill()
    }
}