import spock.lang.Specification
import spock.lang.Unroll

class FixedAmountCouponSpec extends Specification {

    @Unroll
    def "定額で割引される 300円クーポンを #amount 円に適用したときの値引き額は #expected 円"() {
        given: "300円値引きクーポン"
        def sut = new FixedAmountCoupon(BigDecimal.valueOf(300))

        when:
        def discounted = sut.discount(BigDecimal.valueOf(amount))

        then:
        discounted == BigDecimal.valueOf(expected)

        where:
        amount | expected || description
           500 |      300 || "金額 > クーポン値引き額"
           300 |      300 || "金額 = クーポン値引き額"
           299 |      299 || "金額 < クーポン値引き額"
    }
}
