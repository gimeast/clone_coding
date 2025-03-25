package gimeast.restfulapiex1.todo.repository;

import gimeast.restfulapiex1.todo.dto.TodoDTO;
import gimeast.restfulapiex1.todo.entity.TodoEntity;
import gimeast.restfulapiex1.todo.repository.custom.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {
    //dto에 엔티티를 받는 생성자를 만들고, projections를 이용해서 바로 DTO 추출
    @Query("select t from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO(Long mno);
}
