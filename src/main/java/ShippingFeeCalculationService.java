import java.math.BigDecimal;
import java.util.Set;

/**
 * 送料計算ドメインサービス.
 */
public class ShippingFeeCalculationService {

    // 離島に該当する地域の郵便番号頭3桁のセット
    private static final Set<String> REMOTE_AREAS = Set.of("901");

    // 金額による送料割引きのしきい値
    private static final BigDecimal THRESHOLD = BigDecimal.valueOf(3000);

    // 送料
    private static final BigDecimal FEE_DEFAULT = BigDecimal.valueOf(500);
    private static final BigDecimal FEE_DEFAULT_DISCOUNT = BigDecimal.ZERO;
    private static final BigDecimal FEE_REMOTE = BigDecimal.valueOf(1200);
    private static final BigDecimal FEE_REMOTE_DISCOUNT = BigDecimal.valueOf(700);

    public BigDecimal calculateFee(Order order) {
        if (order.getCustomer().getRank() == CustomerRank.PREMIUM) {
            return BigDecimal.ZERO;
        }
        BigDecimal subtotal = order.getSubtotal();
        boolean isRemote = shipsToRemoteIsland(order);
        if (subtotal.compareTo(THRESHOLD) >= 0) {
            return isRemote ? FEE_REMOTE_DISCOUNT : FEE_DEFAULT_DISCOUNT;
        } else {
            return isRemote ? FEE_REMOTE : FEE_DEFAULT;
        }
    }

    private boolean shipsToRemoteIsland(Order order) {
        String postalCode = order.getCustomer().getAddress().getPostalCode();
        String key = postalCode.split("-")[0]; // 3桁
        return REMOTE_AREAS.contains(key);
    }

}
