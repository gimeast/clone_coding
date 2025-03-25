package gimeast.restfulapiex1.todo.repository.custom;

import gimeast.restfulapiex1.todo.dto.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {
    Page<TodoDTO> searchDTO(Pageable pageable);
}
