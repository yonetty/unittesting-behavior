import spock.lang.Specification

// 注文の基本的振舞い
class OrderBasicSpec extends Specification {

    def "デフォルトは明細0件"() {
        given:
        def sut = OrderHelper.anOrderByOrdinaryCustomer()

        when:
        def orderLines = sut.getOrderLines()

        then:
        orderLines == []
    }

    def "明細を追加できる"() {
        given:
        def sut = OrderHelper.anOrderByOrdinaryCustomer()

        def product = new Product("01", "マウス", BigDecimal.valueOf(1500L))
        def line = new OrderLine(product, 1)

        when:
        sut.addOrderLine(line)

        then:
        sut.getOrderLines() == [line]
    }

}
