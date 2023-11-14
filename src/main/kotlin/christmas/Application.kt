package christmas

import java.time.LocalDate
fun main() {

    val inputView = InputView()
    val outputView = OutputView()

    val menu = Menu()
    val badge = Badge()

    val inputDate = inputView.readDate()
    val orderString = inputView.getOrder(menu)
    val order = Order(menu, orderString)

    val date = LocalDate.of(2023, 12, inputDate)
    val event = Event(order, date)
    val eventResult = event.applyEvents()

    outputView.printOrderResult(order, eventResult, badge, inputDate)
    return

}
