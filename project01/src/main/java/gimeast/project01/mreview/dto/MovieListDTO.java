package gimeast.project01.mreview.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieListDTO {
    private Long id;
    private String title;
    private int grade;
    private int replyCount;

    private String uuid;
    private String imgName;
    private String path;


    @QueryProjection
    public MovieListDTO(Long id, String title, int grade, int replyCount, String uuid, String imgName, String path) {
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.replyCount = replyCount;
        this.uuid = uuid;
        this.imgName = imgName;
        this.path = path;
    }
}
