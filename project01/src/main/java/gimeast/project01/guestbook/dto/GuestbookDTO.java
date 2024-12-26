package gimeast.project01.guestbook.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class GuestbookDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;

    @QueryProjection
    public GuestbookDTO(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
