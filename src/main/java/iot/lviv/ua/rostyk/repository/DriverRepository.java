/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.repository
 * Interface: DriverRepository
 */

package iot.lviv.ua.rostyk.repository;

import iot.lviv.ua.rostyk.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Query(value = "CALL CalcAverageRating();", nativeQuery = true)
    Integer getAverageRating();

    @Procedure("AddDriverHasCarRelationship")
    void addDriverHasCarRelationship(String driverName, String carBrand);
}
