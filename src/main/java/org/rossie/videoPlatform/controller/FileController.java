//package org.rossie.videoPlatform.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.rossie.videoPlatform.Service.FileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequiredArgsConstructor
//public class FileController {
//
//    @Autowired
//    public final FileService fileService;
//
//    @PostMapping("api/v1/file/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadFile){
//        try{
//            fileService.uploadFile(uploadFile);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok("File uploaded successfully");
//    }
//
//    // TODO: Implement file download
//    @GetMapping("api/v1/file/download")
//    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename){
//        Resource resource = null;
//        try {
//            resource = fileService.downloadFile(filename);
//            String contentType = "application/octet-stream";
//            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
