package gimeast.project01.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gimeast.project01.board.dto.BoardDTO;
import gimeast.project01.board.dto.BoardListDTO;
import gimeast.project01.board.dto.BoardWithReplyDTO;
import gimeast.project01.board.dto.QBoardDTO;
import gimeast.project01.board.dto.QBoardListDTO;
import gimeast.project01.board.dto.QReplyDTO;
import gimeast.project01.board.dto.ReplyDTO;
import gimeast.project01.board.entity.Board;
import gimeast.project01.board.entity.QBoard;
import gimeast.project01.board.entity.QReply;
import gimeast.project01.board.entity.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardListDTO> findBoardWithRepliesCount(Pageable pageable, String search) {
        QBoard qBoard = QBoard.board;
        QReply qReply = QReply.reply;

        BooleanBuilder builder = new BooleanBuilder();
        if(search != null && !search.isEmpty()) {
            builder.or(qBoard.title.contains(search));
            builder.or(qBoard.content.contains(search));
            builder.or(qBoard.user.userId.contains(search));
        }

        List<BoardListDTO> fetch = queryFactory
                .select(new QBoardListDTO(new QBoardDTO(qBoard.id, qBoard.title, qBoard.content, qBoard.user.userId), qReply.count()))
                .from(qBoard)
                .leftJoin(qReply).on(qReply.board.eq(qBoard))
                .where(builder)
                .groupBy(qBoard)
                .orderBy(qBoard.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Optional<Long> totalBoardCount = Optional.ofNullable(queryFactory
                .select(qBoard.count())
                .from(qBoard)
                .where(builder)
                .fetchOne());

        return new PageImpl<>(fetch, pageable, totalBoardCount.orElse(0L));
    }

    @Override
    public BoardWithReplyDTO findBoardWithReplies(Long id) {
        QBoard qBoard = QBoard.board;
        QReply qReply = QReply.reply;

        BoardDTO board = queryFactory
                .select(new QBoardDTO(qBoard.id, qBoard.title, qBoard.content, qBoard.user.userId))
                .from(qBoard)
                .where(qBoard.id.eq(id))
                .fetchOne();

        List<ReplyDTO> replies = queryFactory
                .select(new QReplyDTO(qReply.id, qReply.text, qReply.user.userId, qReply.regDate))
                .from(qReply)
                .where(qReply.board.id.eq(id))
                .orderBy(qReply.regDate.desc())
                .fetch();

        return new BoardWithReplyDTO(board, replies);
    }
}
