/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.domain
 * Class: Trip
 */

package iot.lviv.ua.rostyk.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Trip {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "start_point")
    private String startPoint;
    @Basic
    @Column(name = "end_point")
    private String endPoint;
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

//    public Long getDriverId() {
//        return driverId;
//    }
//
//    public void setDriverId(Long driverId) {
//        this.driverId = driverId;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id) && Objects.equals(startPoint, trip.startPoint) && Objects.equals(endPoint, trip.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startPoint, endPoint);
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
