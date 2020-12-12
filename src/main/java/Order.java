import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 注文.
 */
public class Order {

    private OrderAmount amount = OrderAmount.ofDefault();

    private List<OrderLine> orderLines = new ArrayList<>();

    private Customer customer;

    private Coupon appliedCoupon;

    public OrderAmount getAmount() {
        return amount;
    }

    public void setAmount(OrderAmount amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Optional<Coupon> getAppliedCoupon() {
        return Optional.ofNullable(appliedCoupon);
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void apply(Coupon coupon) {
        this.appliedCoupon = coupon;
    }

    /**
     * 注文明細リストを取得する.
     *
     * @return 注文明細リスト
     */
    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    /**
     * 注文明細を追加する.
     *
     * @param line 注文明細
     */
    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
    }

    /**
     * 明細合計金額を取得する.
     *
     * @return 各明細の金額の合計
     */
    public BigDecimal getSubtotal() {
        return orderLines.stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 値引き額を計算する.
     * @return 値引き額
     */
    public BigDecimal calculateDiscount() {
        if (appliedCoupon == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal subtotal = getSubtotal();
        return appliedCoupon.discount(subtotal);
    }

}
