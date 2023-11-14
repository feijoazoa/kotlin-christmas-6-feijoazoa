package christmas

class OutputView {
    fun printOrderResult(order: Order, eventResult: EventResult) {
        println("주문 메뉴: ${order.getMenuNames().joinToString("\n ")}")
        println("할인 전 총 주문 금액: ${order.getTotalAmount()}원")
        println("증정 메뉴: ${if (eventResult.gifts.isNotEmpty()) eventResult.gifts.joinToString(", ") else "없음"}")
        println("혜택 내역: ${if (eventResult.benefits.isNotEmpty()) eventResult.benefits.joinToString("\n") else "없음"}")
        println("총 혜택 금액: ${eventResult.totalDiscount}원")
        println("할인 후 예상 결제 금액: ${eventResult.totalAmountAfterDiscount}원")
        println("12월 이벤트 배지: ${if (eventResult.gifts.isNotEmpty()) "샴페인 1개" else "없음"}")
    }
}
