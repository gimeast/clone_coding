package gimeast.project02.note;

import gimeast.project02.note.dto.NoteDTO;
import gimeast.project02.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody NoteDTO noteDTO) {
        log.info("--------------------register----------------------");
        log.info(noteDTO);

        Long id = noteService.register(noteDTO);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> read(@PathVariable Long id) {
        log.info("--------------------read----------------------");
        log.info(id);
        return new ResponseEntity<>(noteService.get(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoteDTO>> getList(String email) {
        log.info("--------------------getList----------------------");
        log.info(email);

        return new ResponseEntity<>(noteService.getAllWithWriter(email), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> modify(@RequestBody NoteDTO noteDTO) {
        log.info("--------------------modify----------------------");
        log.info(noteDTO);

        noteService.modify(noteDTO);

        return new ResponseEntity<>("modified", HttpStatus.OK);
    }
}
