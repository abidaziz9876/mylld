package lld_problems.applycoupononcart;

public class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
