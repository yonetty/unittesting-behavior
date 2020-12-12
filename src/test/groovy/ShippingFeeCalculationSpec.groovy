import spock.lang.Specification
import spock.lang.Unroll

import static CustomerRank.*

class ShippingFeeCalculationSpec extends Specification {

    static final def ADDR_MAINLAND = CustomerHelper.anAddressInMainland()
    static final def ADDR_ISLAND = CustomerHelper.anAddressInRemoteIsland()

    def sut

    def setup() {
        sut = new ShippingFeeCalculationService();
    }

    @Unroll
    def "送料計算が正しい: #rank #address #price => #expectedFee"() {
        given: "所与のランク・住所の顧客による、所与の小計額の注文がある"
        def customer = CustomerHelper.aCustomerWithRankAndAddress(rank, address)
        def order = new Order(customer)
        OrderHelper.addOrderLineWithPriceAndQuantity(order, price, 1)

        when: "送料計算を実行したとき"
        def fee = sut.calculateFee(order)

        then: "期待どおりの送料を得られる"
        fee == BigDecimal.valueOf(expectedFee)

        where: "パラメータ"
        rank    | address       | price || expectedFee
        REGULAR | ADDR_MAINLAND |  2999 ||         500
        REGULAR | ADDR_MAINLAND |  3000 ||           0
        REGULAR | ADDR_ISLAND   |  2999 ||        1200
        REGULAR | ADDR_ISLAND   |  3000 ||         700
        PREMIUM | ADDR_MAINLAND |  2999 ||           0
        PREMIUM | ADDR_MAINLAND |  3000 ||           0
        PREMIUM | ADDR_ISLAND   |  2999 ||           0
        PREMIUM | ADDR_ISLAND   |  3000 ||           0
    }

}
