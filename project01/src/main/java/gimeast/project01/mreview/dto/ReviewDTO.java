package gimeast.project01.mreview.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private double grade;
    private String text;

    private Long movieId;
    private String email;
}
