package gimeast.project01.mreview.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gimeast.project01.mreview.dto.MovieListDTO;
import gimeast.project01.mreview.dto.QMovieListDTO;
import gimeast.project01.mreview.entity.QMovie;
import gimeast.project01.mreview.entity.QMovieImage;
import gimeast.project01.mreview.entity.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MovieListDTO> findMovieWithGradeAndReviewCount(Pageable pageable, String search) {
        QMovie movie = QMovie.movie;
        QReview review = QReview.review;
        QMovieImage image = QMovieImage.movieImage;
        QMovieImage subImage = new QMovieImage("subImage");

        BooleanBuilder builder = new BooleanBuilder();

        if(search != null && !search.isEmpty()) {
            builder.or(movie.title.contains(search));
        }

        JPQLQuery<LocalDateTime> latestImageSubQuery = JPAExpressions
                .select(subImage.regDate.max())
                .from(subImage)
                .where(subImage.movie.eq(movie));

        List<MovieListDTO> fetch = queryFactory
                .select(new QMovieListDTO(movie.id, movie.title, review.grade.avg().intValue(), review.count().intValue(), image.uuid, image.imgName, image.path))
                .from(movie)
                .leftJoin(review).on(review.movie.eq(movie))
                .leftJoin(image).on(image.movie.eq(movie).and(image.regDate.eq(latestImageSubQuery)))
                .where(builder)
                .groupBy(movie, image)
                .orderBy(movie.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Optional<Long> movieCount = Optional.ofNullable(queryFactory
                .select(movie.count())
                .from(movie)
                .where(builder)
                .fetchOne());

        return new PageImpl<>(fetch, pageable, movieCount.orElse(0L));
    }
}
