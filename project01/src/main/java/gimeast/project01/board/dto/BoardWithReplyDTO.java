package gimeast.project01.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardWithReplyDTO {
    private BoardDTO boardDTO;
    private List<ReplyDTO> replies;
}
