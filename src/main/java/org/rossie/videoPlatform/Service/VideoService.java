package org.rossie.videoPlatform.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.dto.VideoResponseDto;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {


    private final VideoRepository videoRepository;
    private final AzureBlobStorageService azureBlobStorageService;

    public String uploadVideo(MultipartFile file, String title, String description) throws IOException {
        String fileName = title + "_" + file.getOriginalFilename();

        String sasUrl = azureBlobStorageService.uploadFile(file, fileName);

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setUrl(sasUrl);

        videoRepository.save(video);

        return sasUrl;
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
