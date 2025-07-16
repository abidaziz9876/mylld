package lld_problems.applycoupononcart;
import java.time.LocalDate;

public abstract class AbstractCoupon implements Coupon {
    protected final String code;
    protected final LocalDate expiry;
    protected final int maxGlobalUses;
    protected final int maxUsesPerUser;

    public AbstractCoupon(String code, LocalDate expiry, int maxGlobalUses, int maxUsesPerUser) {
        this.code = code;
        this.expiry = expiry;
        this.maxGlobalUses = maxGlobalUses;
        this.maxUsesPerUser = maxUsesPerUser;
    }

    public String getCode() {
        return code;
    }

   

    public boolean isApplicable(ShoppingCart cart, Customer customer) {
        if (LocalDate.now().isAfter(expiry)) return false;
        return CouponManager.getInstance().canUseCoupon(code, customer, maxGlobalUses, maxUsesPerUser);
    }

    public boolean isExclusive() {
        return false;
    }
}

