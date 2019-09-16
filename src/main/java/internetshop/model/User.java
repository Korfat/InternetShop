package internetshop.model;

import internetshop.idgenerator.UserIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private List<Order> orders;
    private Bucket bucket;

    public User(String name) {
        this.id = UserIdGenerator.getGeneratedId();
        orders = new ArrayList<>();
        this.name = name;
        this.bucket = new Bucket(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
}
