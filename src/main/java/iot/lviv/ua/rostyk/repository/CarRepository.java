/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.repository
 * Interface: CarRepository
 */

package iot.lviv.ua.rostyk.repository;

import iot.lviv.ua.rostyk.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findCarByBrand(String brand);

    @Procedure("CreateTablesWithCursor")
    void createTablesWithCursor();
}
