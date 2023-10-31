package YummyTummy

import YummyTummy.*

class PremiumCustomer(customerId: Int, name: Name, address: Address, phoneNumber: String) : Customer(customerId, name, address, phoneNumber),
    Billing {
    private var hasMembershipCard: Boolean = true
    private var freeDelivery: Boolean = true // Set free delivery to true
    private var specialDiscount: Double = 0.3 // 5% special discount

    fun hasMembershipCard(): Boolean {
        return hasMembershipCard
    }

    fun setMembershipCard(hasMembershipCard: Boolean) {
        this.hasMembershipCard = hasMembershipCard
    }

    fun hasFreeDelivery(): Boolean {
        return freeDelivery
    }

    fun setFreeDelivery(freeDelivery: Boolean) {
        this.freeDelivery = freeDelivery
    }

    fun getSpecialDiscount(): Double {
        return specialDiscount
    }

    fun setSpecialDiscount(specialDiscount: Double) {
        this.specialDiscount = specialDiscount
    }

    override fun payBill(order: Order): Double {
        val totalBill = order.calculateBill()
        val discountAmount = totalBill * specialDiscount
        val discountedBill = totalBill - discountAmount
        return discountedBill
    }
}
