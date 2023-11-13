package christmas

fun main() {
    TODO("프로그램 구현")
    val inputView = InputView()
    val menu = Menu()

    val date = inputView.readDate()
    val order = inputView.getOrder(menu)
}
