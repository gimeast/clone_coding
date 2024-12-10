package gimeast.project01.guestbook.mapper;

import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GuestbookMapper {
    GuestbookMapper INSTANCE = Mappers.getMapper(GuestbookMapper.class);

    Guestbook dtoToEntity(GuestbookDTO guestbookDTO);
    List<Guestbook> dtoListToEntityList(List<GuestbookDTO> guestbookDTOS);
}
