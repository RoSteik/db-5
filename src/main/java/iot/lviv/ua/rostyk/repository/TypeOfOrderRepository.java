/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.repository
 * Interface: TypeOfOrderRepository
 */

package iot.lviv.ua.rostyk.repository;

import iot.lviv.ua.rostyk.domain.TypeOfOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfOrderRepository extends JpaRepository<TypeOfOrder, Integer> {
}
