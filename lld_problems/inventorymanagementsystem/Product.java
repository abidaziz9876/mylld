package lld_problems.inventorymanagementsystem;

public abstract class Product {
  private String sku;
  private String name;
  private double price;
  private int quantity;
  private int threshold;
  private ProductCategory category;

  // Getters and setters
  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public ProductCategory getCategory() {
    return category;
  }

  public void setCategory(ProductCategory category) {
    this.category = category;
  }

  public void setThreshold(int threshold){
      this.threshold=threshold;
  }

  public int getThreshold(){
    return this.threshold;
  }
  public void addStock(int amount) {
    if (amount > 0) {
        this.quantity += amount;
    }
  }

  public void removeStock(int amount) {
    if (amount > 0 && amount <= this.quantity) {
        this.quantity -= amount;
    } else {
        throw new IllegalArgumentException("Invalid stock removal amount: " + amount);
    }
  }
}