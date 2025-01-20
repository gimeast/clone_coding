package gimeast.project02.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class NoteDTO {

    private Long id;

    private String title;

    private String content;

    private String writerEmail;

    private LocalDateTime regDate, modDate;

    public NoteDTO(Long id, String title, String content, String writerEmail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerEmail = writerEmail;
    }

    public NoteDTO(Long id, String title, String content, String writerEmail, LocalDateTime regDate, LocalDateTime modDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerEmail = writerEmail;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
