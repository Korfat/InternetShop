package internetshop.model;

import internetshop.idgenerator.OrderIdGenerator;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.id = OrderIdGenerator.getGeneratedId();
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
