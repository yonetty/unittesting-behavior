import spock.lang.Specification
import spock.lang.Unroll

class OrderLineSpec extends Specification {

    @Unroll
    def "明細金額が正しく計算される: #unitPrice x #quantity should be #expectedAmount"() {
        given:
        def product = OrderHelper.aProductPriced(unitPrice)
        def sut = new OrderLine(product, quantity)

        when:
        def amount = sut.getAmount()

        then:
        amount == expectedAmount

        where:
        unitPrice | quantity || expectedAmount
              100 |        0 ||              0
                0 |        5 ||              0
              100 |        1 ||            100
              100 |        2 ||            200
    }
}
