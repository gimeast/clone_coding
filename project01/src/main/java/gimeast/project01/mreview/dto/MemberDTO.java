package gimeast.project01.mreview.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String nickname;
}
