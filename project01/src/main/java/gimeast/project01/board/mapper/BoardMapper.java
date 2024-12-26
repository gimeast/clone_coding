package gimeast.project01.board.mapper;

import gimeast.project01.board.dto.BoardDTO;
import gimeast.project01.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "replies", ignore = true)
    Board dtoToEntity(BoardDTO boardDTO);
}
