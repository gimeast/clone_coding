package gimeast.project01.mreview.controller;

import gimeast.project01.common.PageRequestDTO;
import gimeast.project01.mreview.dto.MovieDTO;
import gimeast.project01.mreview.dto.MovieListDTO;
import gimeast.project01.mreview.dto.ReviewDTO;
import gimeast.project01.mreview.service.MovieService;
import gimeast.project01.mreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping("/register")
    public void register() {}

    @PostMapping("/register")
    public ResponseEntity<Boolean> saveForm(String title, MultipartFile[] uploadFiles) {
        boolean result = movieService.saveMovieWithImages(title, uploadFiles);

        if(result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequest, @RequestParam(required = false, defaultValue = "") String search, Model model) {
        Page<MovieListDTO> pageList = movieService.getMovieList(pageRequest, search);

        model.addAttribute("pageList", pageList);
        model.addAttribute("search", search);
        model.addAttribute("pageRequest", pageRequest);
    }

    @GetMapping("/read")
    public void read(Long id, Model model) {
        MovieDTO movieDTO = movieService.getMovieWithAll(id);
        model.addAttribute("dto", movieDTO);
    }

    @PostMapping("/review")
    public ResponseEntity<Long> saveReview(@RequestBody ReviewDTO reviewDTO) {
        Long id = movieService.saveReview(reviewDTO);

        if (id > 0) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
