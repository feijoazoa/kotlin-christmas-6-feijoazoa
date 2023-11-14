package christmas

class Event(private val order: Order) {
    private val discount = Discount(order)

    fun applyEvents(): EventResult {
        if(order.getTotalAmount() < 10000) {
            return EventReslt(emptyList(), emptyList(), 0, order.getTotalAmount())
        }
    }
}