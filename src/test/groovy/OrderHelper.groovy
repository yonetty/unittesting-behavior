class OrderHelper {

    // 一般的な顧客(レギュラー会員、離島ではない)からの注文
    static def anOrderByOrdinaryCustomer() {
        new Order(CustomerHelper.aRegularCustomerFromMainland())
    }

    static def addOrderLineWithPriceAndQuantity(Order order, int unitPrice, int quantity) {
        def anOrderLine = new OrderLine(aProductPriced(unitPrice), quantity)
        order.addOrderLine(anOrderLine)
        order
    }

    static def aProductPriced(int unitPrice) {
        new Product("any", "any", BigDecimal.valueOf(unitPrice))
    }
}
