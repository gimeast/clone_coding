package gimeast.restfulapiex1.todo.repository.custom.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gimeast.restfulapiex1.todo.dto.TodoDTO;
import gimeast.restfulapiex1.todo.entity.QTodoEntity;
import gimeast.restfulapiex1.todo.repository.custom.TodoSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class TodoSearchImpl implements TodoSearch {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TodoDTO> searchDTO(Pageable pageable) {
        QTodoEntity todoEntity = QTodoEntity.todoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(todoEntity.mno.gt(0L));

        List<TodoDTO> fetch = queryFactory
                .select(
                        Projections.constructor(TodoDTO.class, todoEntity)
//                        Projections.bean(TodoDTO.class,
//                                todoEntity.mno,
//                                todoEntity.title,
//                                todoEntity.writer,
//                                todoEntity.dueDate)
                )
                .from(todoEntity)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Optional<Long> count = Optional.ofNullable(queryFactory
                .select(todoEntity.count())
                .from(todoEntity)
                .where(builder)
                .fetchOne());
        return new PageImpl<>(fetch, pageable, count.orElse(0L));
    }
}
