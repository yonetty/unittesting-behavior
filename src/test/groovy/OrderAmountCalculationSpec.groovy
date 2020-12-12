import spock.lang.Specification

class OrderAmountCalculationSpec extends Specification {

    def sut

    def setup() {
        sut = new OrderAmountCalculationService()
    }

    def "注文金額が計算できる"() {
        given:
        def order = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(order, 1500, 1)
        def shipping = BigDecimal.valueOf(500)

        when:
        def amount = sut.calculate(order, shipping)

        then:
        amount.tax == BigDecimal.valueOf(200)
        amount.total == BigDecimal.valueOf(2200)
    }

    def "注文金額が計算できる:クーポンあり"() {
        given:
        def order = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(order, 1500, 1)
        // クーポンのスタブ実装
        order.apply {BigDecimal.valueOf(700)}
        def shipping = BigDecimal.valueOf(500)

        when:
        def amount = sut.calculate(order, shipping)

        then:
        amount.discount == BigDecimal.valueOf(700)
        amount.taxable == BigDecimal.valueOf(1300) // (1500 - 700 + 500) * 0.1
        amount.tax == BigDecimal.valueOf(130) // 1300 * 0.1
        amount.total == BigDecimal.valueOf(1430) // (1300 + 130)
    }
}
