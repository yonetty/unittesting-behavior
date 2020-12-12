import java.math.BigDecimal;

public class FixedAmountCoupon implements Coupon {

    private BigDecimal discount;

    public FixedAmountCoupon(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public BigDecimal discount(BigDecimal amount) {
        BigDecimal result = amount.subtract(discount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            return amount;
        }
        return discount;
    }
}
