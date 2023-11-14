package christmas

class Discount(private val order: Order) {
    fun christmasDdayDiscount(dayOfMonth: Int): Int {
        return 1000 + (dayOfMonth - 1) * 100
    }
    fun weekdayDiscount(): Int {
        return order.getMenuNames(MenuCategory.DESSERT) * 2023
    }
    fun weekendDiscount(): Int {
        return order.getMenuNames(MenuCategory.MAIN) * 2023
    }

}

data class EventResult(
    val benefits: List<String>,
    val gifts: List<String>,
    val totalDiscount: Int,
    val totalAmountAfterDiscount: Int
)