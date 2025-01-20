package gimeast.project02.note.service;

import gimeast.project02.member.entity.Member;
import gimeast.project02.member.repository.MemberRepository;
import gimeast.project02.note.dto.NoteDTO;
import gimeast.project02.note.entity.Note;
import gimeast.project02.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long register(NoteDTO noteDTO) {
        log.info("===============================");
        String writerEmail = noteDTO.getWriterEmail();

        Member writer = memberRepository.findByEmail(writerEmail).orElseThrow();

        Note note = Note.builder()
                .id(noteDTO.getId())
                .title(noteDTO.getTitle())
                .content(noteDTO.getContent())
                .writer(writer)
                .build();

        noteRepository.save(note);
        log.info("note: {}", note);
        return note.getId();
    }

    @Override
    public NoteDTO get(Long id) {
        Optional<NoteDTO> withWriter = noteRepository.getWithWriter(id);
        return withWriter.orElse(null);
    }

    @Override
    public void modify(NoteDTO noteDTO) {
        Optional<Note> byId = noteRepository.findById(noteDTO.getId());

        if (byId.isPresent()) {
            Note note = byId.get();
            note.changeTitle(noteDTO.getTitle());
            note.changeContent(noteDTO.getContent());
            noteRepository.save(note);
        }
    }

    @Override
    public void remove(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<NoteDTO> getAllWithWriter(String writerEmail) {
        return noteRepository.getList(writerEmail);
    }
}
