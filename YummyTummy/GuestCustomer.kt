package YummyTummy

class GuestCustomer(customerId: Int, name: Name, address: Address, phoneNumber: String) : Customer(customerId, name, address, phoneNumber),
    Billing {
    private var deliveryCharges: Double = 55.5

    fun getDeliveryCharges(): Double {
        return deliveryCharges
    }

    fun setDeliveryCharges(deliveryCharges: Double) {
        this.deliveryCharges = deliveryCharges
    }

    override fun payBill(order: Order): Double {
        return order.calculateBill() + deliveryCharges
    }
}