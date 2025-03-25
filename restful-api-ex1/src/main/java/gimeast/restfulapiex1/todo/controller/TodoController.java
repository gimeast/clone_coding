package gimeast.restfulapiex1.todo.controller;

import gimeast.restfulapiex1.common.dto.PageRequestDTO;
import gimeast.restfulapiex1.common.dto.PageResponseDTO;
import gimeast.restfulapiex1.todo.dto.TodoDTO;
import gimeast.restfulapiex1.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDTO> register(@RequestBody @Validated TodoDTO todoDTO) {
        log.info("register........");
        TodoDTO result = todoService.register(todoDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{mno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long mno) {
        return ResponseEntity.ok(todoService.read(mno));
    }

    @PutMapping("/{mno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long mno, @RequestBody TodoDTO todoDTO) {
        todoDTO.setMno(mno);
        TodoDTO modifiedTodo = todoService.modify(todoDTO);
        return ResponseEntity.ok(modifiedTodo);
    }

    @DeleteMapping("/{mno}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable Long mno) {
        todoService.remove(mno);
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<TodoDTO>> list(@Validated PageRequestDTO pageRequestDTO) {
        Page<TodoDTO> result = todoService.getList(pageRequestDTO);
        PageResponseDTO<TodoDTO> resultDTO = PageResponseDTO.<TodoDTO>builder()
                .content(result.getContent())
                .currentPage(result.getNumber())
                .pageSize(result.getSize())
                .totalItems(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();

        return ResponseEntity.ok(resultDTO);

    }
}
