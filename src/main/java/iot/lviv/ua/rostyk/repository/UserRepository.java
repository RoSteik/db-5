/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.repository
 * Interface: UserRepository
 */

package iot.lviv.ua.rostyk.repository;

import iot.lviv.ua.rostyk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Procedure("UserParamInsert")
    User addUserWithProcedure(String name, Integer rating);
}
