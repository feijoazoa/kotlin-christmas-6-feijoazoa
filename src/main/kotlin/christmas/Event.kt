package christmas

import java.time.LocalDate

class Event(private val order: Order) {
    private val discount = Discount(order)

    fun applyEvents(): EventResult {
        if (order.getTotalAmount() < 10000) {
            return EventResult(emptyList(), emptyList(), 0, order.getTotalAmount())
        }

        val benefits = mutableListOf<String>()
        val gifts = mutableListOf<String>()
        var totalDiscount = 0

        val date = LocalDate.ofEpochDay(order.date.toLong())
        val dayOfWeek = date.dayOfWeek.name
        val dayOfMonth = date.dayOfMonth

        // 크리스마스 디데이 할인 이벤트
        if (date.monthValue == 12 && dayOfMonth in 1..25) {
            val discountAmount = discount.christmasDdayDiscount(dayOfMonth)
            benefits.add("크리스마스 디데이 할인: -$discountAmount")
            totalDiscount += discountAmount
        }

        //평일 할인 이벤트
        if (dayOfWeek in listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY")) {
            val discountAmount = discount.weekdayDiscount()
            benefits.add("평일 할인: -$discountAmount")
            totalDiscount += discountAmount
        }
        //주말 할인 이벤트
        if (dayOfWeek in listOf("FRIDAY", "SATURDAY")) {
            val discountAmount = discount.weekendDiscount()
            benefits.add("주말 할인: -$discountAmount")
            totalDiscount += discountAmount
        }
        // 특별 할인
        if (dayOfMonth in listOf(3, 10, 17, 24, 25, 31)) {
            val discountAmount = 1000
            benefits.add("특별 할인: -$discountAmount")
            totalDiscount += discountAmount
        }

        val totalAmountAfterDiscount = order.getTotalAmount() - totalDiscount

        return EventResult(benefits, gifts, totalDiscount, totalAmountAfterDiscount)
    }
}
