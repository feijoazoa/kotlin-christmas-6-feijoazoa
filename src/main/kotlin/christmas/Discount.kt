package christmas

class Discount(private val order: Order) {





}

data class EventResult(
    val benefits: List<String>,
    val gifts: List<String>,
    val totalDiscount: Int,
    val totalAmountAfterDiscount: Int
)