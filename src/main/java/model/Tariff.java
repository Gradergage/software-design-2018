package model;

import javax.persistence.*;
@Entity(name = "Tariff")
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariffs_generator")
    @SequenceGenerator(name="tariffs_generator", sequenceName = "tariffs_id_seq", allocationSize = 1)
    @Column(name = "tariff_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    public Tariff() {
    }

    public Tariff(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
