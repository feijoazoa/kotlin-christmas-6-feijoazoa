package christmas

import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale

class Event(private val order: Order, private val date: LocalDate) {
    private val discount = Discount(order)
    private val benefits = mutableListOf<String>()
    private val gifts = mutableListOf<String>()

    fun applyEvents(): EventResult {
        if (order.getTotalAmount() < MINIMUM_TOTAL_ORDER_PRICE) {
            return EventResult(emptyList(), emptyList(), 0, order.getTotalAmount())
        }
        var totalDiscount = 0
        var giftEventPrice = 0

        val dayOfWeek = date.dayOfWeek.name
        val dayOfMonth = date.dayOfMonth

        totalDiscount += applyChristmasDdayEvent(dayOfMonth)
        totalDiscount += applyWeekdayEvent(dayOfWeek)
        totalDiscount += applySpecialDayEvent(dayOfMonth)
        giftEventPrice = applyGiftEvent()

        val totalAmountAfterDiscount = order.getTotalAmount() - totalDiscount

        return EventResult(benefits, gifts, totalDiscount + giftEventPrice, totalAmountAfterDiscount)
    }
    private fun applyChristmasDdayEvent(dayOfMonth: Int): Int {
        return if (date.monthValue == CHRISTMAS_MONTH && dayOfMonth in CHRISTMAS_DAYS) {
            val discountPrice = discount.christmasDdayDiscount(dayOfMonth)
            benefits.add("크리스마스 디데이 할인: -${discountPrice.format()}원")
            discountPrice
        } else 0
    }
    private fun applyWeekdayEvent(dayOfWeek: String): Int {
        return when (dayOfWeek) {
            in WEEKDAYS -> {
                val weekdaysDiscountPrice = discount.weekdayDiscount()
                benefits.add("평일 할인: -${weekdaysDiscountPrice.format()}원")
                weekdaysDiscountPrice
            }
            in WEEKENDS -> {
                val weekendsDiscountPrice = discount.weekendDiscount()
                benefits.add("주말 할인: -${weekendsDiscountPrice.format()}원")
                weekendsDiscountPrice
            }
            else -> 0
        }
    }
    private fun applySpecialDayEvent(dayOfMonth: Int): Int {
        return if (dayOfMonth in SPECIAL_DAYS) {
            val specialDayDiscountPrice = SPECIAL_DAY_DISCOUNT_PRICE
            benefits.add("특별 할인: -${specialDayDiscountPrice.format()}원")
            specialDayDiscountPrice
        } else 0
    }
    private fun applyGiftEvent(): Int {
        return if (order.getTotalAmount() >= GIFT_EVENT_AMOUNT_THRESHOLD) {
            gifts.add("샴페인 1개")
            val giftEventPrice = GIFT_EVENT_PRICE
            benefits.add("증정이벤트: -${giftEventPrice.format()}원")
            giftEventPrice
        } else 0
    }

    private fun Int.format(): String {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(this)
    }

    companion object {
        const val MINIMUM_TOTAL_ORDER_PRICE = 10000
        const val CHRISTMAS_MONTH = 12
        const val GIFT_EVENT_AMOUNT_THRESHOLD = 120000
        const val SPECIAL_DAY_DISCOUNT_PRICE = 1000
        const val GIFT_EVENT_PRICE = 25000
        val CHRISTMAS_DAYS = 1..25
        val SPECIAL_DAYS = listOf(3, 10, 17, 24, 25, 31)
        val WEEKDAYS = listOf("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY")
        val WEEKENDS = listOf("FRIDAY", "SATURDAY")
    }
}
