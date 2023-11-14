package christmas

import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale



class Event(private val order: Order, private val date: LocalDate) {
    private val discount = Discount(order)

    fun applyEvents(): EventResult {
        if (order.getTotalAmount() < 10000) {
            return EventResult(emptyList(), emptyList(), 0, order.getTotalAmount())
        }

        val benefits = mutableListOf<String>()
        val gifts = mutableListOf<String>()
        var totalDiscount = 0
        var giftEventAmount = 0

        val dayOfWeek = date.dayOfWeek.name
        val dayOfMonth = date.dayOfMonth

        // 크리스마스 디데이 할인 이벤트
        if (date.monthValue == 12 && dayOfMonth in 1..25) {
            val discountAmount = discount.christmasDdayDiscount(dayOfMonth)
            benefits.add("크리스마스 디데이 할인: -${discountAmount.format()}원")
            totalDiscount += discountAmount
        }

        //평일 할인 이벤트
        if (dayOfWeek in listOf("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY")) {
            val discountAmount = discount.weekdayDiscount()
            benefits.add("평일 할인: -${discountAmount.format()}원")
            totalDiscount += discountAmount
        }
        //주말 할인 이벤트
        if (dayOfWeek in listOf("FRIDAY", "SATURDAY")) {
            val discountAmount = discount.weekendDiscount()
            benefits.add("주말 할인: -${discountAmount.format()}원")
            totalDiscount += discountAmount
        }
        // 특별 할인
        if (dayOfMonth in listOf(3, 10, 17, 24, 25, 31)) {
            val discountAmount = 1000
            benefits.add("특별 할인: -${discountAmount.format()}원")
            totalDiscount += discountAmount
        }

        // 증정 이벤트
        if (order.getTotalAmount() >= 120000) {
            gifts.add("샴페인 1개")
            giftEventAmount = 25000
            benefits.add("증정이벤트: -${giftEventAmount.format()}원")
        }

        val totalAmountAfterDiscount = order.getTotalAmount() - totalDiscount

        return EventResult(benefits, gifts, totalDiscount + giftEventAmount, totalAmountAfterDiscount)
    }
    private fun Int.format(): String {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(this)
    }
}
