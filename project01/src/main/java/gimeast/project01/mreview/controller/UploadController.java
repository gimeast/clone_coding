package gimeast.project01.mreview.controller;

import gimeast.project01.common.FileDeleteDTO;
import gimeast.project01.common.UploadResultDTO;
import gimeast.project01.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class UploadController {

    private final FileUtils fileUtils;

    @PostMapping("/upload")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        List<UploadResultDTO> uploadResultDTOList = fileUtils.uploadFiles(uploadFiles);

        if(ObjectUtils.isEmpty(uploadResultDTOList)) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(uploadResultDTOList);
        }
    }

    @GetMapping("/upload")
    public void upload() {

    }
    
    @PostMapping("/files/delete")
    public ResponseEntity<Boolean> removeFiles(@RequestBody FileDeleteDTO fileDeleteDTO) {
        boolean deleteStatus = fileUtils.deleteFiles(fileDeleteDTO.getFileName());

        if(deleteStatus) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
