package YummyTummy

import YummyTummy.*

class RegularCustomer(customerId: Int, name: Name, address: Address, phoneNumber: String) : Customer(customerId, name, address, phoneNumber),
    Billing {
    private var discount: Double = 0.1

    fun getDiscount(): Double {
        return discount
    }

    fun setDiscount(discount: Double) {
        this.discount = discount
    }

    override fun payBill(order: Order): Double {
        val totalBill = order.calculateBill()
        return totalBill - (totalBill * discount)
    }
}