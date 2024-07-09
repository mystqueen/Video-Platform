package org.rossie.videoPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.Service.VideoService;
import org.rossie.videoPlatform.model.Video;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("v1/video/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getVideo(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        Video nextVideo = videoService.getNextVideo(id);
        Video previousVideo = videoService.getPreviousVideo(id);

        Map<String, Object> response = new HashMap<>();
        response.put("video", video);
        response.put("nextVideo", nextVideo);
        response.put("previousVideo", previousVideo);

        return ResponseHandler.success(response, "Video Page Data Retrieved", HttpStatus.OK);
    }

}
