/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.domain
 * Class: Driver
 */

package iot.lviv.ua.rostyk.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Driver {
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
    @Column(name = "completed_orders")
    private Integer completedOrders;
    @Basic
    @Column(name = "is_vacant")
    private Byte isVacant;

    @ManyToMany
    @JoinTable(name = "driver_has_car", schema = "rostyks", joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false))
    private List<Car> cars;

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

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Byte getIsVacant() {
        return isVacant;
    }

    public void setIsVacant(Byte isVacant) {
        this.isVacant = isVacant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(name, driver.name) && Objects.equals(rating, driver.rating) && Objects.equals(completedOrders, driver.completedOrders) && Objects.equals(isVacant, driver.isVacant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, completedOrders, isVacant);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
