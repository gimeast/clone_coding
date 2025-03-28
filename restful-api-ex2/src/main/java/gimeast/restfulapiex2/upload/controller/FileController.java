package gimeast.restfulapiex2.upload.controller;

import gimeast.restfulapiex2.upload.exception.UploadNotSupportedException;
import gimeast.restfulapiex2.upload.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final UploadUtil uploadUtil;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files") MultipartFile[] files) {
        log.info("upload file....");

        if (files == null || files.length == 0) {
            throw new UploadNotSupportedException("No files to upload");
        }

        for (MultipartFile file : files) {
            uploadUtil.checkFileType(file.getOriginalFilename());
        }

        List<String> result = uploadUtil.upload(files);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<Void> deleteFIle(@PathVariable String fileName) {
        log.info("delete file: {}", fileName);
        uploadUtil.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }

}
