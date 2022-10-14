package com.example.project.Controller;

import com.example.project.Domain.FileDB;
import com.example.project.Domain.User;
import com.example.project.Messages.ResponseFile;
import com.example.project.Service.FileStorageService;
import com.example.project.Service.PaymentService;
import com.example.project.Service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static javax.xml.bind.DatatypeConverter.parseLong;

@RestController
@Controller
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders={"Accept"})
@RequestMapping("/api/users")
@ResponseStatus(HttpStatus.OK)
public class FileController {

    @Autowired
    private FileStorageService storageService;
@Autowired
private PaymentService paymentService;

    @PostMapping("/upload/{userId}")
    @ResponseBody
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,@PathVariable("userId") Long userId) throws IOException {
       String s=paymentService.checkingPayment(userId);
       Integer a=paymentService.amountPayment(userId);
       Integer c=paymentService.countPayment(userId);
     Long t=storageService.totalsize1(userId);
     if ((t>=5000000*c) && s.equals("paid") && a.equals(5)){
         storageService.store(file, userId);
         return (file.getOriginalFilename()+ " Succesfully saved");
     }
     /*
     else if (t>=5000000*c) {
         return ("could not upload the file!");
     }*/
     else {
         storageService.store(file, userId);

         return (file.getOriginalFilename()+ " Succesfully saved");

     }

    }

    @GetMapping("/files/{userId}")
    public ResponseEntity<List<ResponseFile>> getListFilesById(@PathVariable("userId") Long userId) {
        Long t1=storageService.totalsize1(userId);
        Integer c1=paymentService.countPayment(userId);

        List<ResponseFile> files = storageService.filebyUserId(userId).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/users/files/")
                    .path(String.valueOf(userId)+"/")
                    .path(dbFile.getDocId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length) ;






        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
 /*   @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/users/files/")
                    .path(dbFile.getDocId())
                    .toUriString();

            return new ResponseFile(

                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/
    @GetMapping("/files/{userId}/{docId}")
    public ResponseEntity<byte[]> getFile(@PathVariable("userId") Long userId,@PathVariable("docId") String docId) {
        FileDB fileDB = storageService.getFile(docId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

}
