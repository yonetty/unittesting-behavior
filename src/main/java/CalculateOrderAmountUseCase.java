import java.math.BigDecimal;

/**
 * 注文金額を計算するユースケース.
 */
public class CalculateOrderAmountUseCase {

    private ShippingFeeCalculationService shippingCalcService;

    private OrderAmountCalculationService amountCalcService;

    public CalculateOrderAmountUseCase(ShippingFeeCalculationService shippingCalcService, OrderAmountCalculationService amountCalcService) {
        this.shippingCalcService = shippingCalcService;
        this.amountCalcService = amountCalcService;
    }

    public void calculate(Order order) {
        BigDecimal shipping = shippingCalcService.calculateFee(order);
        OrderAmount amount = amountCalcService.calculate(order, shipping);
        order.setAmount(amount);
    }

}
