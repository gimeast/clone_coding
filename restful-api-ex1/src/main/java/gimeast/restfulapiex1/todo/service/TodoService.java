package gimeast.restfulapiex1.todo.service;

import gimeast.restfulapiex1.common.dto.PageRequestDTO;
import gimeast.restfulapiex1.exception.EntityNotFoundException;
import gimeast.restfulapiex1.todo.dto.TodoDTO;
import gimeast.restfulapiex1.todo.entity.TodoEntity;
import gimeast.restfulapiex1.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoDTO register(TodoDTO todoDTO) {
        TodoEntity todoEntity = todoDTO.toEntity();
        todoRepository.save(todoEntity);
        return new TodoDTO(todoEntity);
    }

    public TodoDTO read(Long mno) {
        Optional<TodoDTO> result = todoRepository.getDTO(mno);
        return result.orElseThrow(() -> new EntityNotFoundException("Todo " + mno + " not found"));
    }

    @Transactional
    public void remove(Long mno) {
        Optional<TodoEntity> result = todoRepository.findById(mno);
        TodoEntity todoEntity = result.orElseThrow(() -> new EntityNotFoundException("Todo " + mno + " not found"));
        todoRepository.delete(todoEntity);
    }

    @Transactional
    public TodoDTO modify(TodoDTO todoDTO) {
        Optional<TodoEntity> result = todoRepository.findById(todoDTO.getMno());
        TodoEntity todoEntity = result.orElseThrow(() -> new EntityNotFoundException("Todo " + todoDTO.getMno() + " not found"));

        todoEntity.changeTitle(todoDTO.getTitle());
        todoEntity.changeWriter(todoDTO.getWriter());
        todoEntity.changeDueDate(todoDTO.getDueDate());
        return new TodoDTO(todoEntity);
    }

    public Page<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = pageRequestDTO.getPageable(sort);
        return todoRepository.searchDTO(pageable);
    }
}
