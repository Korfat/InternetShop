package internetshop.model;

import internetshop.idgenerator.ItemIdGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", columnDefinition = "INT")
    private Long id;
    private String name;
    private String model;
    @Column(name = "price", columnDefinition = "DECIMAL")
    private Double price;

    public Item() {

    }

    public Item(Long id) {
        this.id = id;
    }

    public Item(String name, String model, Double price) {
        this.id = ItemIdGenerator.getGeneratedId();
        this.name = name;
        this.model = model;
        this.price = price;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
