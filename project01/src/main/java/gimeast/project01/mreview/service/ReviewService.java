package gimeast.project01.mreview.service;

import gimeast.project01.mreview.dto.ReviewDTO;
import gimeast.project01.mreview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDTO> getReviews(Long id) {
        return reviewRepository.findAllByMovieId(id);
    }
}
