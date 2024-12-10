package gimeast.project01.guestbook.controller;

import gimeast.project01.guestbook.common.PageRequestDTO;
import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
    private final GuestbookRepository repository;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        System.out.println("pageRequestDTO = " + pageRequestDTO);

        Pageable pageable = PageRequestDTO.builder()
                .page(pageRequestDTO.getPage())
                .size(pageRequestDTO.getSize())
                .build()
                .getPageable(Sort.by("title").descending());

        Page<GuestbookDTO> pageList = repository.findAllGuestbookDTO(pageable);

        model.addAttribute("pageList", pageList);
    }
}
