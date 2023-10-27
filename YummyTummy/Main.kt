package YummyTummy

import java.util.Scanner

fun main() {
    val sc  = Scanner(System.`in`)
    println("------Welcome to ${RestaurantDetails.name}-------  \n Address ${RestaurantDetails.address} \n Phone Number ${RestaurantDetails.phoneNumber} \n Rating ${RestaurantDetails.rating}!!!! \nNow you can use YummyTummy to place your order.")
    println("-------------------------------------")
    println("Please select the customer type:")
    println("1. Regular Customer")
    println("2. Premium Customer")
    println("3. Guest Customer")
    println("-------------------------------------")
    print("Enter the customer type (1/2/3): ")
    val customerType:Int = sc.nextInt()

    val customer: Customer = when (customerType) {
        1 -> {
            print("Enter YummyTummy.Customer ID: ")
            val customerId: Int = sc.nextInt()
            print("Enter your first name: ")
            val fname: String = sc.next()
            print("Enter your last name: ")
            val lname: String = sc.next()
            val name = Name(fname, lname)
            print("Enter your city : ")
            val city: String = sc.next()
            print("Enter your state : ")
            val state: String = sc.next()
            val address = Address(city,state)
            print("Enter Customer Phone Number: ")
            val phoneNumber: String = sc.next()

            RegularCustomer(customerId, name, address, phoneNumber)

        }
        2 -> {
            print("Enter Customer ID: ")
            val customerId: Int = sc.nextInt()
            print("Enter your first name: ")
            val fname: String = sc.next()
            print("Enter your last name: ")
            val lname: String = sc.next()
            val name = Name(fname, lname)
            print("Enter your city : ")
            val city: String = sc.next()
            print("Enter your state : ")
            val state: String = sc.next()
            val address = Address(city,state)
            print("Enter your Phone Number: ")
            val phoneNumber: String = sc.next()
            PremiumCustomer(customerId, name, address, phoneNumber)

        }
        3 -> {
            print("Enter Customer ID: ")
            val customerId: Int = sc.nextInt()
            print("Enter your first name: ")
            val fname: String = sc.next()
            print("Enter your last name: ")
            val lname: String = sc.next()
            val name = Name(fname, lname)
            print("Enter your city : ")
            val city: String = sc.next()
            print("Enter your state : ")
            val state: String = sc.next()
            val address = Address(city,state)
            print("Enter your Phone Number: ")
            val phoneNumber: String = sc.next()

            GuestCustomer(customerId,name,address,phoneNumber)


        }
        else -> {
            println("Invalid customer type.")
            return
        }
    }



    val customerName = customer.getName()
    println("\nHello, ${customer.getName().firstName} ${customer.getName().lastName}! Let's place your order.\n")

    val selectedItems = mutableListOf<Int>()
    while (true) {
        println("Select an item by entering its number, or '0' to finish ordering.")

        println("---------------Menu------------------")
        Menu.menuItems.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name} - rupee ${item.price}")
        }
        
        val userInput: Int = sc.nextInt()
        if (userInput == 0) {
            println("Thank you for visiting YummyTummy!!!")
            break
        } else if (userInput in 1..Menu.menuItems.size) {

            selectedItems.add(userInput - 1)

            if (customerType == 1) {

                (customer as RegularCustomer).setDiscount(0.1)
                val orderId = customer.orders.size + 1
                customer.placeOrder(orderId, selectedItems)
                val order = customer.orders.last()

                println("\nYour order details:")
                order.displayOrderDetails()
                println("Discount Applied "+customer.getDiscount())
                val totalBill = customer.payBill(order)
                println("\nTotal Bill: rupee $totalBill")

            } else if (customerType == 2) {

                (customer as PremiumCustomer).setSpecialDiscount(0.3)
                val orderId = customer.orders.size + 1
                customer.placeOrder(orderId, selectedItems)
                val order = customer.orders.last()

                println("\nYour order details:")
                order.displayOrderDetails()
                println( "Special Discount Applied "+customer.getSpecialDiscount())
                val totalBill = customer.payBill(order)
                println("\nTotal Bill: rupee $totalBill")

            } else if (customerType == 3) {

                (customer as GuestCustomer).setDeliveryCharges(2.3)
                val orderId = customer.orders.size + 1
                customer.placeOrder(orderId, selectedItems)
                val order = customer.orders.last()

                println("\nYour order details:")
                order.displayOrderDetails()
                println("Delivery charges Applied "+customer.getDeliveryCharges())
                val totalBill = customer.payBill(order)
                println("\nTotal Bill: rupee $totalBill")

            }

        } else {
            println("Invalid selection. Please choose a valid item.")
        }
    }

}

