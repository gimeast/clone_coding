package gimeast.project01.mreview.controller;

import gimeast.project01.common.PageRequestDTO;
import gimeast.project01.mreview.dto.MovieDTO;
import gimeast.project01.mreview.dto.ReviewDTO;
import gimeast.project01.mreview.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping("/detail")
    public ResponseEntity<MovieDTO> getMovie(Long id) {
        MovieDTO movieDTO = movieService.getMovie(id);

        if (!ObjectUtils.isEmpty(movieDTO)) {
            return ResponseEntity.ok(movieDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/review")
    public ResponseEntity<Page<ReviewDTO>> getReviewList(Long id, PageRequestDTO pageRequestDTO) {
        System.out.println("id = " + id);
        System.out.println("pageRequestDTO = " + pageRequestDTO);
        Page<ReviewDTO> reviews = movieService.getReviewList(id, pageRequestDTO);
        System.out.println("reviews = " + reviews.getContent());
        return ResponseEntity.ok(reviews);
    }

}
