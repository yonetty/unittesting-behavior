import java.math.BigDecimal;

/**
 * 注文金額計算ドメインサービス
 */
public class OrderAmountCalculationService {

    /**
     * 注文金額を計算する.
     * @param order 注文
     * @param shipping 送料
     * @return 注文金額
     */
    public OrderAmount calculate(Order order, BigDecimal shipping) {
        BigDecimal subtotal = order.getSubtotal();
        BigDecimal discount = order.calculateDiscount();
        OrderAmount total = new OrderAmount(subtotal, shipping, discount);
        return total;
    }
}
