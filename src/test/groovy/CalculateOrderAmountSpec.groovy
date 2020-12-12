import spock.lang.Specification

class CalculateOrderAmountSpec extends Specification {

    // 注：モックを使ったユースケースのテストは余り意味がない
    def "注文金額は明細小計+送料+消費税"() {
        given: "注文"
        def order = OrderHelper.anOrderByOrdinaryCustomer()
        OrderHelper.addOrderLineWithPriceAndQuantity(order, 1500, 1)

        and: "送料"
        def shippingFee = BigDecimal.valueOf(500)

        and: "モックとSUT"
        def shippingCalcServiceMock = Mock(ShippingFeeCalculationService)
        def orderAmountCalcServiceMock = Mock(OrderAmountCalculationService)
        def sut = new CalculateOrderAmountUseCase(shippingCalcServiceMock, orderAmountCalcServiceMock)

        when: "ユースケース実行"
        sut.calculate(order)

        then: "相互作用の検証"
        1 * shippingCalcServiceMock.calculateFee(order) >> shippingFee
        1 * orderAmountCalcServiceMock.calculate(order, shippingFee)
            >> new OrderAmount(BigDecimal.valueOf(1500), shippingFee, BigDecimal.ZERO)

        and: "計算結果の検証"
        def amount = order.getAmount()
        amount.getTotal() == BigDecimal.valueOf(2200)
    }

}
