package gimeast.project01.board.repository;

import gimeast.project01.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserId(String userId);

    @Query("select u.userId from User u")
    List<String> findAllUserIds();
}
