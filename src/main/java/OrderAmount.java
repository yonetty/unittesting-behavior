import java.math.BigDecimal;

/**
 * 注文金額.
 */
public class OrderAmount {

    // サンプルのため消費税率は一律10%で固定
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(10L, 2);

    private final BigDecimal subtotal;

    private final BigDecimal shipping;

    private final BigDecimal discount;

    private final BigDecimal taxable;

    private final BigDecimal tax;

    private final BigDecimal total;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getShipping() {
        return shipping;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTaxable() {
        return taxable;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderAmount(BigDecimal subtotal, BigDecimal shipping, BigDecimal discount ) {
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.discount = discount;
        taxable = subtotal.subtract(discount).add(shipping);
        tax = taxable.multiply(TAX_RATE);
        total = taxable.add(tax);
    }

    public static OrderAmount ofDefault() {
        return new OrderAmount(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

}
