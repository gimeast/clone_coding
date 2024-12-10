package gimeast.project01.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDTO {
    private Long id;
    private String memoText;
    private Date currentDate;
}
