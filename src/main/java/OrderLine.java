import java.math.BigDecimal;

/**
 * 注文明細.
 */
public class OrderLine {

    private Product product;

    private Integer quantity;

    private BigDecimal amount;

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderLine(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.amount = product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
