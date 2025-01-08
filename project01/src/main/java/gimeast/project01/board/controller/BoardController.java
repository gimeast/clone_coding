package gimeast.project01.board.controller;

import gimeast.project01.board.dto.BoardDTO;
import gimeast.project01.board.dto.BoardListDTO;
import gimeast.project01.board.dto.BoardWithReplyDTO;
import gimeast.project01.board.dto.ReplyDTO;
import gimeast.project01.board.service.BoardService;
import gimeast.project01.common.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequest, @RequestParam(required = false, defaultValue = "") String search, Model model) {
        Page<BoardListDTO> pageList = boardService.getBoardList(pageRequest, search);

        model.addAttribute("pageList", pageList);
        model.addAttribute("search", search);
        model.addAttribute("pageRequest", pageRequest);
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        BoardWithReplyDTO dto = boardService.getBoardWithReply(id);
        model.addAttribute("dto", dto);

        List<String> allUserIds = boardService.getAllUserIds();
        model.addAttribute("allUserIds", allUserIds);
        return "board/detail";
    }

    @GetMapping("/write")
    public String write(@RequestParam(required = false) Long id, Model model) {
        BoardDTO dto = new BoardDTO();

        if (id != null) {
            dto = boardService.getBoard(id);
        }

        model.addAttribute("dto", dto);

        List<String> allUserIds = boardService.getAllUserIds();
        model.addAttribute("allUserIds", allUserIds);
        return "board/write";
    }

    @PostMapping("/write")
    public ResponseEntity<Long> submit(@RequestBody BoardDTO dto) {
        Long id = boardService.saveBoard(dto);

        return ResponseEntity.ok(id);
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity<Long> replySubmit(@PathVariable Long id, @RequestBody ReplyDTO replyDTO) {
        Long boardId = boardService.saveReply(id, replyDTO);
        return ResponseEntity.ok(boardId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(null);
    }
}
