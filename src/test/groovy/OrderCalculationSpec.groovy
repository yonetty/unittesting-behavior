import spock.lang.Specification

// 注文の料金計算の振舞い
class OrderCalculationSpec extends Specification {

    def "明細が0件の注文額は0円"() {
        given:
        def sut = OrderHelper.anOrderByOrdinaryCustomer()

        when:
        def total = sut.getAmount()

        then:
        total.getTotal() == BigDecimal.ZERO
    }

    def "小計は明細金額の合計となる: 明細1件"() {
        given:
        def sut = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 1500, 1)

        when:
        def subtotal = sut.getSubtotal()

        then:
        subtotal == BigDecimal.valueOf(1500L)
    }

    def "小計は明細金額の合計となる: 明細複数件"() {
        given:
        def sut = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 1500, 1)
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 200, 2)

        when:
        def subtotal = sut.getSubtotal()

        then:
        subtotal == BigDecimal.valueOf(1900) // (1500 * 1) + (200 * 2)
    }

    def "値引き額を取得できる: クーポン適用済 スタブ利用版"() {
        given: "定額クーポンが適用された注文"
        def sut = new Order(null) // このテストでは顧客は不要なのでnull
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 1000, 1)
        and: "モックの適用"
        def coupon = Mock(Coupon)
        sut.apply(coupon)

        when:
        def discount = sut.calculateDiscount()

        then:
        1 * coupon.discount(BigDecimal.valueOf(1000)) >> BigDecimal.valueOf(300)
        discount == BigDecimal.valueOf(300)
    }

    def "値引き額を取得できる: クーポン適用済"() {
        given: "定額クーポンが適用された注文"
        def sut = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 1000, 1)
        def coupon = new FixedAmountCoupon(BigDecimal.valueOf(300))
        sut.apply(coupon)

        when:
        def discount = sut.calculateDiscount()

        then:
        discount == BigDecimal.valueOf(300)
    }

    def "値引き額を取得できる: クーポン適用済2 小計<クーポン額"() {
        given: "定額クーポンが適用された注文"
        def sut = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 299, 1)
        def coupon = new FixedAmountCoupon(BigDecimal.valueOf(300))
        sut.apply(coupon)

        when:
        def discount = sut.calculateDiscount()

        then: "値引き額は小計額と同額"
        discount == BigDecimal.valueOf(299)
    }

    def "値引き価格を取得できる: クーポン未適用"() {
        given: "定額クーポンが適用された注文"
        def sut = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(sut, 1000, 1)

        when:
        def discount = sut.calculateDiscount()

        then:
        discount == BigDecimal.ZERO
    }
}
