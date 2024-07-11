package org.rossie.videoPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.Service.VideoService;
import org.rossie.videoPlatform.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/")
public class VideoController {
    @Autowired
    private VideoService videoService;

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @GetMapping("v1/video/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getVideo(@PathVariable Long id) {
        logger.info("Received request for video with id: {}", id);

        Video video = videoService.getVideoById(id);
        if (video == null) {
            logger.error("Video not found for id: {}", id);
            return ResponseHandler.error(null, "Video not found", HttpStatus.NOT_FOUND);
        }

        Video nextVideo = videoService.getNextVideo(id);
        Video previousVideo = videoService.getPreviousVideo(id);

        Map<String, Object> response = new HashMap<>();
        response.put("video", video);
        response.put("nextVideo", nextVideo);
        response.put("previousVideo", previousVideo);

        logger.info("Returning video details: {}", video);
        return ResponseHandler.success(response, "Video Page Data Retrieved", HttpStatus.OK);
    }

    @PostMapping("v1/video/share")
    public ResponseEntity<Object> shareVideo(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String videoUrl = request.get("videoUrl");

        if (email == null || videoUrl == null) {
            return ResponseHandler.error(null, "Email and video URL must be provided", HttpStatus.BAD_REQUEST);
        }

        boolean isSent = videoService.sendVideoEmail(email, videoUrl);

        if (isSent) {
            return ResponseHandler.success(null, "Video link sent successfully", HttpStatus.OK);
        } else {
            return ResponseHandler.error(null, "Failed to send video link", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
