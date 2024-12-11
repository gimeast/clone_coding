package gimeast.project01.guestbook.controller;

import gimeast.project01.guestbook.common.PageRequestDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import gimeast.project01.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
    private final GuestbookService guestbookService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequest, Model model, @RequestParam(required = false, defaultValue = "") String search) {
        Page<Guestbook> pageList = guestbookService.getGuestbooks(pageRequest, search);

        model.addAttribute("pageList", pageList);
        model.addAttribute("search", search);
        model.addAttribute("pageRequest", pageRequest);
    }

}
