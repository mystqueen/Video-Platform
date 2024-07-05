package org.rossie.videoPlatform.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.exception.EntityNotFoundException;
import org.rossie.videoPlatform.model.Video;
import org.rossie.videoPlatform.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    public void uploadFile(MultipartFile file) throws IOException {
        File targetFile = new File(
                resourceLoader.getResource("classpath:templates").getFile() + "/" + file.getOriginalFilename());
        if (targetFile.createNewFile()) {
            file.transferTo(targetFile);
            videoRepository.save(new Video("api/v1/video/" + file.getOriginalFilename()));
            System.out.println("File Created" + targetFile.getAbsolutePath());
        } else {
            System.out.println("File already exists");
        }
    }

    public Object getVideoLink(Long videoId){
        Optional<Video> videoById = videoRepository.findById(videoId);
        if (videoById.isEmpty()){
            throw new EntityNotFoundException("Video with this ID does not exist");
        }
        return videoById;
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Video with this ID does not exist"));
    }

    public Video getNextVideo(Long id) {
        return videoRepository.findFirstByIdGreaterThan(id);
    }

    public Video getPreviousVideo(Long id) {
        return videoRepository.findFirstByIdLessThanOrderByIdDesc(id);
    }
}
