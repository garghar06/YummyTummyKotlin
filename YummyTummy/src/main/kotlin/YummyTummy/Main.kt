package YummyTummy

import java.util.Scanner

fun main() {
    val sc  = Scanner(System.`in`)
    println("------Welcome to ${RestaurantDetails.restaurantName}-------  \n        Address ${RestaurantDetails.restaurantAddress}\n        Phone Number ${RestaurantDetails.restaurantPhoneNumber} \n        Rating ${RestaurantDetails.restaurantRating}")
    println("-------------------------------------")
    println("Please select the customer type:")
    println("1. Regular Customer")
    println("2. Premium Customer")
    println("3. Guest Customer")
    println("-------------------------------------")
    print("Enter the customer type (RC/PC/GC): ")
    try {
        val customerType:String= sc.next()

        val customer: Customer = when (customerType) {
            "RC" -> {
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
            "PC" -> {
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
            "GC" -> {
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
                throw InvalidCustomerType("Invalid customer type. Please select 1 for Regular, 2 for Premium, or 3 for Guest.")

            }
        }

        val customerName = customer.getName()
        println("\nHello, ${customer.getName().firstName} ${customer.getName().lastName}! Let's place your order.\n")

        val selectedItems = mutableListOf<Int>()

        while (true) {
            println("Select an item from the menu (1-${Menu.menuItems.size}), or 0 to finish:")
            println("---------------Menu------------------")
            Menu.menuItems.forEachIndexed { index, item ->
                println("${index + 1}. ${item.name} - rupee ${item.price}")
            }

            val selectedItem: Int = sc.nextInt()
            if (selectedItem == 0) {
                break
            }
            if (selectedItem in 1..Menu.menuItems.size) {
                selectedItems.add(selectedItem - 1)
            } else {
                throw InvalidMenuItem("Invalid menu item selection. Please choose a valid item from the menu.")
            }
        }
        val orderedItems = selectedItems.map { Menu.menuItems[it] }

        val order = Order(1, customer, orderedItems)

        println("Do you want to dine in at our restaurant or order through the app? (dining/app): ")
        val choice:String = sc.next()

        RestaurantDetails(RestaurantDetails.restaurantName, RestaurantDetails.restaurantAddress, RestaurantDetails.restaurantPhoneNumber, RestaurantDetails.restaurantRating)
            .handleOrderChoice(choice, customer, order)
    }
    catch (e: InvalidCustomerType){
        println("Error: ${e.message}")
    }
    catch (e: InvalidMenuItem){
        println("Error : ${e.message}")
    }

}

fun RestaurantDetails.handleOrderChoice(choice: String, customer: Customer, order: Order) {
    if (customer is RegularCustomer || customer is PremiumCustomer) {

        if (choice.equals("dining")) {
            dineIn(order.orderId, customer, order.orderedItems)
        } else if (choice.equals("app")) {
            order.displayOrderDetails()
            val totalBill = customer.payBill(order)
            saveBillingDetails(order, totalBill)

        } else {
            println("Invalid choice. Please select 'dining' or 'app'.")
        }
    } else {
        order.displayOrderDetails()
        val totalBill = customer.payBill(order)
        saveBillingDetails(order, totalBill)
    }
}