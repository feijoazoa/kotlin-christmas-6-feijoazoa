package christmas

import java.text.NumberFormat
import java.util.Locale
class OutputView {
    fun printOrderResult(order: Order, eventResult: EventResult, badge: Badge, inputDate: Int) {
        val badgeName = badge.assignBadge(eventResult)

        println("12월 ${inputDate}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
        println("<주문 메뉴>")
        order.getMenuNames().forEach { println("$it ${order.orderedItems[it]}개") }
        println("\n<할인 전 총주문 금액>")
        println("${order.getTotalAmount().format()}원")
        println("\n<증정 메뉴>")
        println(if (eventResult.gifts.isNotEmpty()) eventResult.gifts.joinToString(", ") else "없음")
        println("\n<혜택 내역>")
        println(if (eventResult.benefits.isNotEmpty()) eventResult.benefits.joinToString("\n") else "없음")
        println("\n<총혜택 금액>")
        println("-${eventResult.totalDiscount.format()}원")
        println("\n<할인 후 예상 결제 금액>")
        println("${eventResult.totalAmountAfterDiscount.format()}원")
        println("\n<12월 이벤트 배지>")
        println(badgeName)
    }

    private fun Int.format(): String {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(this)
    }
}
