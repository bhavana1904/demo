package com.example.project.Service;

import java.io.IOException;
import java.util.stream.Stream;

import com.example.project.Domain.FileDB;
import com.example.project.Domain.User;
import com.example.project.Repository.FileDBRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private UserRepository userRepository;



    public FileDB store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());


        return fileDBRepository.save(FileDB);
    }


public FileDB store(MultipartFile file,Long userId) throws IOException {

    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
     Long docSize=file.getSize();
User user1=new User(userId);
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(),docSize,user1);


    return fileDBRepository.save(FileDB);//,userId);

}


public FileDB getFile(String docId) {
        return fileDBRepository.findById(docId).get();
    }
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
    public Stream<FileDB> filebyUserId(Long userId){
       return fileDBRepository.filebyUserId(userId).stream();

   }
   public Long totalsize1(Long userId) {
   return fileDBRepository.totalsize(userId);
   }
}
