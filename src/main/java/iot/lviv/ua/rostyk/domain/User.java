/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.domain
 * Class: User
 */

package iot.lviv.ua.rostyk.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "rating")
    private Integer rating;

    @Basic
    @Column(name = "type_of_order_id")
    private Integer typeOfOrderId;


    public Integer getId() {
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(rating, user.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }

    public Integer getTypeOfOrderId() {
        return typeOfOrderId;
    }

    public void setTypeOfOrderId(Integer typeOfOrderId) {
        this.typeOfOrderId = typeOfOrderId;
    }
}
