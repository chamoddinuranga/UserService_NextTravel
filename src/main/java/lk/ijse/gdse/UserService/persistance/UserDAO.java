package lk.ijse.gdse.UserService.persistance;

import lk.ijse.gdse.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,String> {
    User save(User user);
    User getUserByUserId(String id);
    void deleteByUserId(String id);
}
