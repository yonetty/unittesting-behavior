import spock.lang.Specification

class OrderAmountSpec extends Specification {

    def "デフォルトのオブジェクト"() {
        when:
        def sut = OrderAmount.ofDefault()

        then:
        sut.getTotal() == BigDecimal.ZERO
    }
}
