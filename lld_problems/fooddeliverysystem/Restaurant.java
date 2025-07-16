package lld_problems.fooddeliverysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    String id;
    String name;
    List<MenuItem> menu;
    String address;
    public Restaurant(String id, String name, String address, List<MenuItem> menu) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    public Restaurant(String name, String address) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.menu = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.remove(item);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
    
}
