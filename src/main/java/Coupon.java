import java.math.BigDecimal;

/**
 * クーポンインタフェース.
 */
public interface Coupon {

    /**
     * 金額に対して値引きを行う.
     * @param amount 金額
     * @return 値引き金額
     */
    BigDecimal discount(BigDecimal amount);

}
