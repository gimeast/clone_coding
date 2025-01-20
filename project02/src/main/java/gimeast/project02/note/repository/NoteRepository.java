package gimeast.project02.note.repository;

import gimeast.project02.note.dto.NoteDTO;
import gimeast.project02.note.entity.Note;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @EntityGraph(attributePaths = {"writer"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select new gimeast.project02.note.dto.NoteDTO(n.id, n.title, n.content, n.writer.email) from Note n where n.id = :id")
    Optional<NoteDTO> getWithWriter(Long id);

    @EntityGraph(attributePaths = {"writer"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select new gimeast.project02.note.dto.NoteDTO(n.id, n.title, n.content, n.writer.email, n.regDate, n.modDate) from Note n where n.writer.email = :email")
    List<NoteDTO> getList(String email);
}
