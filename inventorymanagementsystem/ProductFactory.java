// public class ProductFactory {
//     public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity, int threshold) {
//         switch (category) {
//             case ELECTRONICS:
//                 return new ElectronicsProduct(sku, name, price, quantity,threshold);
//             case CLOTHING:
//                 return new ClothingProduct(sku, name, price, quantity,threshold);
//             default:
//                 throw new IllegalArgumentException(
//                         "Unsupported product category: " + category);
//         }
//     }
// }
