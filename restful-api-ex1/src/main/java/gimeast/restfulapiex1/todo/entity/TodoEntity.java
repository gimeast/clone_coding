package gimeast.restfulapiex1.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "tbl_todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String writer;

    private LocalDate dueDate;

    @Builder
    public TodoEntity(Long mno, String title, String writer, LocalDate dueDate) {
        this.mno = mno;
        this.title = title;
        this.writer = writer;
        this.dueDate = dueDate;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeWriter(String writer) {
        this.writer = writer;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
