package org.rossie.videoPlatform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private ResourceLoader resourceLoader;
    public void uploadFile(MultipartFile file) throws IOException {
        File file1 = new File(
                resourceLoader.getResource("classpath:templates").getFile() + "/" + file.getOriginalFilename());
        if (file1.createNewFile()) {
            System.out.println("File Created" + file1.getAbsolutePath());
        } else {
            System.out.println("File already exists");
        }
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(file1));
        stream.write(file.getBytes());
        stream.close();
    }

    public Resource downloadFile(String filename) throws IOException{
        final Resource fileResource = resourceLoader.getResource("classpath:templates/" + filename);
        return fileResource;
    }
}
