package com.lvonce.lightserver.api;

import com.lvonce.lightserver.api.domain.Account;
import com.lvonce.lightserver.dal.domain.User;
import com.lvonce.lightserver.general.file.FileStorageService;
import com.lvonce.lightserver.general.file.UploadFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class Example {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        return "order id : " + id;
    }


    @GetMapping("/user-example")
    // Get请求使用 @RequestParam来抽取你关心的参数，一般不使用@RequestBody
    public ResponseEntity<User> getUserExample(@RequestParam Long id) {
        if (id.longValue() == 1L) {
            User user = new User();
            user.setId(1L);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user-example")
    public ResponseEntity<String> postUserExample(@RequestBody Account account) {
        return new ResponseEntity<>(account.getAccount(), HttpStatus.OK);
    }

    private Optional<UploadFileResponse> saveFile(MultipartFile file) {
        try {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/file-example/") //这个需要个GetMapping对应
                    .path(fileName)
                    .toUriString();
            UploadFileResponse response = new UploadFileResponse();
            response.setFileName(fileName);
            response.setFileDownloadUri(fileDownloadUri);
            response.setFileType(file.getContentType());
            response.setSize(file.getSize());
            return Optional.of(response);
        } catch (IOException ex) {
            log.warn("saveFile: warn : {}", ex.getMessage());
            return Optional.of(null);
        }
    }

    @PostMapping("/file-example")
    @ResponseBody
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        Optional<UploadFileResponse> response = saveFile(file);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/file-example/uploadMultipleFiles")
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        List<UploadFileResponse> responseList = Arrays.asList(files)
                .stream()
                .map(file -> saveFile(file))
                .filter(response -> response.isPresent())
                .map(response -> response.get())
                .collect(Collectors.toList());
        if (responseList.size() != 0) {
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/file-example/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource

        try {
            Resource resource = fileStorageService.loadFileAsResource(fileName);
            // Try to determine file's content type
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException ex) {
            log.info("Could not determine file type.");
            return ResponseEntity.badRequest().body(null);
        }
    }
}


