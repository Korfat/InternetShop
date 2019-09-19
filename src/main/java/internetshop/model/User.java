package internetshop.model;

import internetshop.idgenerator.UserIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private List<Order> orders;
    private Bucket bucket = new Bucket(this);

    public User() {
        this.id = UserIdGenerator.getGeneratedId();
        orders = new ArrayList<>();
    }

    public User(String name) {
        this.id = UserIdGenerator.getGeneratedId();
        orders = new ArrayList<>();
        this.name = name;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
