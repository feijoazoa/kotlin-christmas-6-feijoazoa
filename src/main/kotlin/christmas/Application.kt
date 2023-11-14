package christmas

fun main() {

    val inputView = InputView()
    val menu = Menu()
    val orders = Order()

    val date = inputView.readDate()
    val order = inputView.getOrder(menu)
}
