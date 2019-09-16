package internetshop.model;

import internetshop.idgenerator.BucketIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(User user) {
        this.id = BucketIdGenerator.getGeneratedId();
        this.userId = user.getId();
        items = new ArrayList<>();
    }

    public void clearItems() {
        items.clear();
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
