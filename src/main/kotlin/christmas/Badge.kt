package christmas

class Badge {
    fun assignBadge(eventResult: EventResult): String {
        return when {
            eventResult.totalDiscount >= 20000 -> "산타"
            eventResult.totalDiscount >= 10000 -> "트리"
            eventResult.totalDiscount >= 5000 -> "별"
            else -> "없음"
        }
    }
}
