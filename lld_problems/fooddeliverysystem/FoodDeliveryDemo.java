package lld_problems.fooddeliverysystem;

import java.util.List;


/*
1-The food delivery service should allow customers to browse restaurants, view menus, and place orders.
2-Restaurants should be able to manage their menus, prices, and availability.
3-Delivery agents should be able to accept and fulfill orders.
4-The system should handle order tracking and status updates.
5-The system should support multiple payment methods.
6-The system should handle concurrent orders and ensure data consistency.
7-The system should be scalable and handle a high volume of orders.
8-The system should provide real-time notifications to customers, restaurants, and delivery agents
*/

public class FoodDeliveryDemo {
    public static void main(String[] args) {
        FoodDeliveryService foodDeliveryService = FoodDeliveryService.getInstance();

        // Register customers
        Customer customer1 = foodDeliveryService.registerCustomer("John Doe", "john@example.com", "1234567890");
        Customer customer2 = foodDeliveryService.registerCustomer("Jane Smith", "jane@example.com", "9876543210");

        // Register restaurants
        Restaurant restaurant1 = foodDeliveryService.registerRestaurant("Restaurant 1", "Address 1");
        foodDeliveryService.addMenuItem(restaurant1.getId(), "Burger", "Delicious burger", 9.99);
        foodDeliveryService.addMenuItem(restaurant1.getId(), "Sushi", "Cheesy pizza", 12.99);

        Restaurant restaurant2 = foodDeliveryService.registerRestaurant("Restaurant 2", "Address 2");
        foodDeliveryService.addMenuItem(restaurant2.getId(), "Pizza", "Fresh sushi", 15.99);
        foodDeliveryService.addMenuItem(restaurant2.getId(), "Ramen", "Delicious ramen", 10.99);

        // Register delivery agents
        DeliveryAgent agent1 = foodDeliveryService.registerDeliveryAgent("Agent 1", "9999999999");
        DeliveryAgent agent2 = foodDeliveryService.registerDeliveryAgent("Agent 2", "8888888888");

        // Get Available Restaurants
        for (String restaurantNames: foodDeliveryService.getAvailableRestaurants()) {
            System.out.println(restaurantNames);
        }

        // Get Restaurant Menu
        for (String restaurantMenu: foodDeliveryService.getRestaurantMenu(restaurant2.getId())) {
            System.out.println(restaurantMenu);
        }
        
        // Place an order
        Order order = foodDeliveryService.placeOrder(customer1.getId(), restaurant1.getId(), List.of("Burger", "Sushi"));

        // Update order status
        foodDeliveryService.updateOrderStatus(order.getId(), OrderStatus.CONFIRMED);

        // Assign Delivery Agent
        foodDeliveryService.assignDeliveryAgent(order.getId());

        // Cancel an order
        Order order2 = foodDeliveryService.placeOrder(customer2.getId(), restaurant1.getId(), List.of("Burger", "Sushi"));
        foodDeliveryService.cancelOrder(order2.getId());
    }
}
