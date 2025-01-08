package gimeast.project01.guestbook.controller;

import gimeast.project01.common.dto.PageRequestDTO;
import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.repository.GuestbookRepository;
import gimeast.project01.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Slf4j
public class GuestbookController {
    private final GuestbookService service;
    private final GuestbookRepository repository;

    @GetMapping("/write")
    public void write(@RequestParam(required = false) Long id, Model model) {
        GuestbookDTO dto = new GuestbookDTO();

        if (id != null) { // 수정 시 데이터 로드
            dto = service.getGuestbook(id);
        }

        model.addAttribute("dto", dto);
    }

    @PostMapping("/write")
    public ResponseEntity<Long> submit(@RequestBody GuestbookDTO dto) {
        Long id = service.saveGuestbook(dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequest, Model model, @RequestParam(required = false, defaultValue = "") String search) {
        Page<GuestbookDTO> pageList = service.getGuestbooks(pageRequest, search);

        model.addAttribute("pageList", pageList);
        model.addAttribute("search", search);
        model.addAttribute("pageRequest", pageRequest);
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        GuestbookDTO dto = service.getGuestbook(id);
        model.addAttribute("dto", dto);
        return "guestbook/detail";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("success");
    }
}
