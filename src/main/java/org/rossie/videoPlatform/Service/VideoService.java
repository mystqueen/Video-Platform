package org.rossie.videoPlatform.Service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.dto.VideoResponseDto;
import org.rossie.videoPlatform.exception.EntityNotFoundException;
import org.rossie.videoPlatform.model.Video;
import org.rossie.videoPlatform.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {


    private final VideoRepository videoRepository;
    private final AzureBlobStorageService azureBlobStorageService;

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    public List<VideoResponseDto> getAllVideos() {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();

        List<VideoResponseDto> videos = new ArrayList<>();
        for (BlobItem blobItem : containerClient.listBlobs()) {
            String url = containerClient.getBlobClient(blobItem.getName()).getBlobUrl();
            videos.add(new VideoResponseDto(blobItem.getName(), url));
        }
        return videos;
    }

    public String uploadVideo(MultipartFile file, String title, String description) throws IOException {
        String sasUrl = azureBlobStorageService.uploadFile(file, title, description);
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setSasUrl(sasUrl);
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
