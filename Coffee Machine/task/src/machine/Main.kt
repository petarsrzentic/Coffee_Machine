package machine

import kotlin.system.exitProcess

var water = 400
var milk = 540
var coffeeBeans = 120
var cups = 9
var money = 550

fun main() {
    do {
        val again = displayMenu()
    } while (again)
}

private fun displayMenu(): Boolean {
    println("Write action (buy, fill, take, remaining, exit): ")
    return when (readLine()!!) {
        "buy" -> {
            sellCoffee()
            true
        }
        "fill" -> {
            refill()
            true
        }
        "take" -> {
            withdraw()
            true
        }
        "remaining" -> {
            machineState()
            true
        }
        "exit" ->
            exitProcess(0)
        else -> false
    }
}

fun machineState() {
    println(
        "The coffee machine has: \n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$coffeeBeans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money\n"
    )
}

fun sellCoffee() {
    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")

    when (readLine()!!.toIntOrNull() ?: displayMenu()) {
        1 -> dispatchCoffee(250, 16, 4, 0)
        2 -> dispatchCoffee(350, 20, 7, 75)
        3 -> dispatchCoffee(200, 12, 6, 100)
    }
    println()
}

fun dispatchCoffee(water: Int, coffeeBeans: Int, money: Int, milk: Int) {

    when {
        machine.water < water -> {
            println("Sorry, not enough water!")
            displayMenu()
        }
        machine.coffeeBeans < coffeeBeans -> {
            println("Sorry, not enough coffeeBeans")
            displayMenu()
        }
        machine.milk < milk -> {
            println("Sorry, not enough milk")
            displayMenu()
        }
        else -> {
            println("I have enough resources, making you a coffee!")

            machine.water -= water
            machine.coffeeBeans -= coffeeBeans
            cups -= 1
            machine.money += money
            machine.milk -= milk
            displayMenu()
        }
    }

}

fun refill() {
    println("Write how many ml of water do you want to add: ")
    water += readLine()!!.toInt()
    println("Write how many ml of milk do you want to add: ")
    milk += readLine()!!.toInt()
    println("Write how many grams of coffee beans do you want to add: ")
    coffeeBeans += readLine()!!.toInt()
    println("Write how many disposable cups of coffee do you want to add: ")
    cups += readLine()!!.toInt()
    println()
}

fun withdraw() {
    println("I gave you $money")
    money -= money
    println()
}