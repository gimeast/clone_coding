package gimeast.project01.sample.controller;

import gimeast.project01.common.dto.FileDeleteDTO;
import gimeast.project01.common.dto.UploadResultDTO;
import gimeast.project01.common.utils.FileUtils;
import gimeast.project01.sample.dto.MemoDTO;
import gimeast.project01.sample.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/sample")
@Log4j2
@RequiredArgsConstructor
public class SampleController {

    private final MemoRepository memoRepository;

    private final FileUtils fileUtils;

    @GetMapping("/ex1")
    public void ex1(@RequestParam(defaultValue = "0") int pageNumber,
                    @RequestParam(defaultValue = "10") int pageSize,
                    Model model) {
        Page<MemoDTO> pageList = memoRepository.findMemoQueryDTOPaging2(PageRequest.of(pageNumber, pageSize));
        System.out.println("pageList.getContent() = " + pageList.getContent());
        model.addAttribute("pageList", pageList);
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam String memoText,
                         Model model) {
        model.addAttribute("id", id);
        model.addAttribute("memoText", memoText);
        return "/sample/detail";
    }

    @GetMapping("/template")
    public void layout() {

    }

    @GetMapping("/upload")
    public void upload() {}

    @PostMapping("/upload")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        List<UploadResultDTO> uploadResultDTOList = fileUtils.uploadFiles(uploadFiles);

        if(ObjectUtils.isEmpty(uploadResultDTOList)) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(uploadResultDTOList);
        }
    }

    @PostMapping("/file/delete")
    public ResponseEntity<Boolean> removeFiles(@RequestBody FileDeleteDTO fileDeleteDTO) {
        boolean deleteStatus = fileUtils.deleteFiles(fileDeleteDTO.getFileName());

        if(deleteStatus) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
