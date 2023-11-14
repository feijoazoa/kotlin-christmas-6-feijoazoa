package christmas

class Badge {
    fun assignBadge(eventResult: EventResult): String {
        return when {
            eventResult.totalDiscount >= SANTA_BADGE_THRESHOLD -> "산타"
            eventResult.totalDiscount >= TREE_BADGE_THRESHOLD -> "트리"
            eventResult.totalDiscount >= STAR_BADGE_THRESHOLD -> "별"
            else -> "없음"
        }
    }
    companion object {
        const val SANTA_BADGE_THRESHOLD = 20000
        const val TREE_BADGE_THRESHOLD = 10000
        const val STAR_BADGE_THRESHOLD = 5000
    }
}
