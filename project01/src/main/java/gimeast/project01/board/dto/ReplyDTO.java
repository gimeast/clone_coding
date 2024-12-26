package gimeast.project01.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyDTO {
    private Long id;
    private String text;
    private String userId;
    private LocalDateTime regdate;

    @QueryProjection
    public ReplyDTO(Long id, String text, String userId, LocalDateTime regdate) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.regdate = regdate;
    }
}
