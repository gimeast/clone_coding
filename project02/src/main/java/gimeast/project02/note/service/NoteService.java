package gimeast.project02.note.service;

import gimeast.project02.member.entity.Member;
import gimeast.project02.note.dto.NoteDTO;
import gimeast.project02.note.entity.Note;

import java.util.List;

public interface NoteService {

    Long register(NoteDTO noteDTO);

    NoteDTO get(Long id);

    void modify(NoteDTO noteDTO);

    void remove(Long id);

    List<NoteDTO> getAllWithWriter(String writerEmail);
}
