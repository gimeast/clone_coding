package gimeast.project01.mreview.dto;

import com.querydsl.core.annotations.QueryProjection;
import gimeast.project01.common.dto.UploadResultDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieListDTO {
    private Long id;
    private String title;
    private float grade;
    private int reviewCount;

    private UploadResultDTO uploadResultDTO;

    @QueryProjection
    public MovieListDTO(Long id, String title, float grade, int reviewCount, UploadResultDTO uploadResultDTO) {
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.reviewCount = reviewCount;
        this.uploadResultDTO = uploadResultDTO;
    }
}
