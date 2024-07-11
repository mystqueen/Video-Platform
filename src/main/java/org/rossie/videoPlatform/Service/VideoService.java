package org.rossie.videoPlatform.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.exception.EntityNotFoundException;
import org.rossie.videoPlatform.model.Video;
import org.rossie.videoPlatform.repository.VideoRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {


    private final VideoRepository videoRepository;
    private final AzureBlobStorageService azureBlobStorageService;
    private final JavaMailSender javaMailSender;

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

    public Object getVideoLink(Long videoId) {
        Optional<Video> videoById = videoRepository.findById(videoId);
        if (videoById.isEmpty()) {
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

    public boolean sendVideoEmail(String email, String videoUrl) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Check out this video!");
            message.setText("Here is a video you might enjoy: " + videoUrl);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
