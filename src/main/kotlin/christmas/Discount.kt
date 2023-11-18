package christmas

class Discount(private val order: Order) {
    fun christmasDdayDiscount(dayOfMonth: Int): Int {
        return BASE_CHRISTMAS_DISCOUNT + (dayOfMonth - 1) * CHRISTMAS_DISCOUNT_INCREMENT
    }
    fun weekdayDiscount(): Int {
        return order.getDessertCount() * WEEKDAY_DISCOUNT_DISCOUNT_PRICE
    }
    fun weekendDiscount(): Int {
        return order.getMainCount() * WEEKEND_DISCOUNT_DISCOUNT_PRICE
    }
    companion object {
        const val BASE_CHRISTMAS_DISCOUNT = 1000
        const val CHRISTMAS_DISCOUNT_INCREMENT = 100
        const val WEEKDAY_DISCOUNT_DISCOUNT_PRICE = 2023
        const val WEEKEND_DISCOUNT_DISCOUNT_PRICE = 2023
    }

}
data class EventResult(
    val benefits: List<String>,
    val gifts: List<String>,
    val totalDiscount: Int,
    val totalAmountAfterDiscount: Int
)
