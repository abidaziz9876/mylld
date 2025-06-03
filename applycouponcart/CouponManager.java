import java.util.*;

public class CouponManager {
    private static final CouponManager instance = new CouponManager();
    private final Map<String, Integer> globalUsage = new HashMap<>();
    private final Map<String, Map<String, Integer>> userUsage = new HashMap<>();

    public static CouponManager getInstance() {
        return instance;
    }

    public boolean canUseCoupon(String code, Customer customer, int globalLimit, int userLimit) {
        int globalUsed = globalUsage.getOrDefault(code, 0);
        int userUsed = userUsage.getOrDefault(code, new HashMap<>())
                                 .getOrDefault(customer.getId(), 0);
        return globalUsed < globalLimit && userUsed < userLimit;
    }

    public void recordUsage(String code, Customer customer) {
        globalUsage.put(code, globalUsage.getOrDefault(code, 0) + 1);
        userUsage.putIfAbsent(code, new HashMap<>());
        Map<String, Integer> userMap = userUsage.get(code);
        userMap.put(customer.getId(), userMap.getOrDefault(customer.getId(), 0) + 1);
    }
}
