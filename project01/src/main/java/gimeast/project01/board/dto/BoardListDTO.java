package gimeast.project01.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardListDTO {
    private BoardDTO boardDTO;
    private Long cnt;

    @QueryProjection
    public BoardListDTO(BoardDTO boardDTO, Long cnt) {
        this.boardDTO = boardDTO;
        this.cnt = cnt;
    }
}
