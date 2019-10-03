package internetshop.model;

import internetshop.idgenerator.BucketIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket() {
        items = new ArrayList<>();
    }

    public Bucket(User user) {
        this.id = BucketIdGenerator.getGeneratedId();
        this.userId = user.getId();
        items = new ArrayList<>();
    }

    public Bucket(Long userId) {
        this.id = BucketIdGenerator.getGeneratedId();
        this.userId = userId;
        items = new ArrayList<>();
    }

    public void clearItems() {
        items.clear();
    }

    public void deleteItem(Long id) {
        items.removeIf(s -> s.getId().equals(id));
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
