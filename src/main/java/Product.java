import java.math.BigDecimal;

/**
 * 商品.
 */
public class Product {

    private String productCode;

    private String productName;

    private BigDecimal unitPrice;

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Product(String productCode, String productName, BigDecimal unitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

}
