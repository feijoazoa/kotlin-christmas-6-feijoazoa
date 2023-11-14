package christmas

import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale

class Event(private val order: Order, private val date: LocalDate) {
    private val discount = Discount(order)
    private val benefits = mutableListOf<String>()
    private val gifts = mutableListOf<String>()

    fun applyEvents(): EventResult {
        if (order.getTotalAmount() < 10000) {
            return EventResult(emptyList(), emptyList(), 0, order.getTotalAmount())
        }
        var totalDiscount = 0
        var giftEventAmount = 0

        val dayOfWeek = date.dayOfWeek.name
        val dayOfMonth = date.dayOfMonth

        totalDiscount += applyChristmasEvent(dayOfMonth)
        totalDiscount += applyWeekdayEvent(dayOfWeek)
        totalDiscount += applySpecialDayEvent(dayOfMonth)
        giftEventAmount = applyGiftEvent()

        val totalAmountAfterDiscount = order.getTotalAmount() - totalDiscount

        return EventResult(benefits, gifts, totalDiscount + giftEventAmount, totalAmountAfterDiscount)
    }
    private fun applyChristmasEvent(dayOfMonth: Int): Int {
        return if (date.monthValue == 12 && dayOfMonth in 1..25) {
            val discountAmount = discount.christmasDdayDiscount(dayOfMonth)
            benefits.add("크리스마스 디데이 할인: -${discountAmount.format()}원")
            discountAmount
        } else 0
    }
    private fun applyWeekdayEvent(dayOfWeek: String): Int {
        return when (dayOfWeek) {
            in listOf("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY") -> {
                val discountAmount = discount.weekdayDiscount()
                benefits.add("평일 할인: -${discountAmount.format()}원")
                discountAmount
            }
            in listOf("FRIDAY", "SATURDAY") -> {
                val discountAmount = discount.weekendDiscount()
                benefits.add("주말 할인: -${discountAmount.format()}원")
                discountAmount
            }
            else -> 0
        }
    }
    private fun applySpecialDayEvent(dayOfMonth: Int): Int {
        return if (dayOfMonth in listOf(3, 10, 17, 24, 25, 31)) {
            val discountAmount = 1000
            benefits.add("특별 할인: -${discountAmount.format()}원")
            discountAmount
        } else 0
    }
    private fun applyGiftEvent(): Int {
        return if (order.getTotalAmount() >= 120000) {
            gifts.add("샴페인 1개")
            val giftEventAmount = 25000
            benefits.add("증정이벤트: -${giftEventAmount.format()}원")
            giftEventAmount
        } else 0
    }

    private fun Int.format(): String {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(this)
    }
}
