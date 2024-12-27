package gimeast.project01.mreview.repository;

import gimeast.project01.mreview.entity.Member;
import gimeast.project01.mreview.entity.Movie;
import gimeast.project01.mreview.entity.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Test
    @DisplayName("더미데이터 생성")
    void createDummyData() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            long movieId = (long) (Math.random() * 37) + 1;
            long memberId = (long) (Math.random() * 100) + 1;

            Movie movie = Movie.builder().id(movieId).build();
            Member member = Member.builder().id(memberId).build();

            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("감상내용: ..." + i)
                    .build();

            reviewRepository.save(review);
        });
    }
}