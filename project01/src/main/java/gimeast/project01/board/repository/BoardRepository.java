package gimeast.project01.board.repository;

import gimeast.project01.board.entity.Board;
import gimeast.project01.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser(User user);
}
