package org.rossie.videoPlatform.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.dto.VideoDto;
import org.rossie.videoPlatform.exception.EntityNotFoundException;
import org.rossie.videoPlatform.model.Video;
import org.rossie.videoPlatform.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@Component
@Transactional
@RequiredArgsConstructor
public class FileService {

    @Autowired
    private VideoRepository videoRepository;
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

    public Object getVideoLink(Long videoId){
        Optional<Video> videoById = videoRepository.findById(videoId);
        if (videoById.isEmpty()){
            throw new EntityNotFoundException("Video with this ID does not exist");
        }
        return videoById;
    }
}
