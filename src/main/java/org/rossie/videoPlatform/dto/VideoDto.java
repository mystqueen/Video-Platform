package org.rossie.videoPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rossie.videoPlatform.model.Video;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
    private Long id;
    private Long adminId;
    private String title;
    private String description;
    private String url;

    public Video toVideo() {

        return new Video(id, title, description, url);
    }

//    private MultipartFile file;
//
//    public MultipartFile getVideo() {
//        return null;
//    }
}
