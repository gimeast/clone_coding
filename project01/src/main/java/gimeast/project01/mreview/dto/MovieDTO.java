package gimeast.project01.mreview.dto;

import gimeast.project01.common.UploadResultDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private float grade;
    private int reviewCount;

    private List<UploadResultDTO> uploadResultDTOList = new ArrayList<>();
    private List<ReviewDTO> reviewDTOList = new ArrayList<>();

    public MovieDTO(Long id, String title, float grade, int reviewCount) {
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.reviewCount = reviewCount;
    }
}
