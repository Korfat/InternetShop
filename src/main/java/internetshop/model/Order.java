package internetshop.model;

import internetshop.idgenerator.OrderIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Order() {
        items = new ArrayList<>();
    }

    public Order(Long id) {
        this.id = id;
        items = new ArrayList<>();
    }

    public Order(Long userId, List<Item> items) {
        this.id = OrderIdGenerator.getGeneratedId();
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
