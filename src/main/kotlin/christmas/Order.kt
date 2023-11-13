package christmas

class Order(private val date: Int, private val menuItems: List<Pair<MenuItem, Int>>) {
    private var totalPrice: Int = 0
    init {
        calculateTotalAmount()
    }

    private fun calculateTotalAmount() {
        totalPrice = menuItems.sumOf { it.first.price * it.second }
    }

    fun getTotalAmount(): Int {
        return totalPrice
    }

    fun getMenuNames(): List<String> {
        return menuItems.map { it.first.name }
    }

}