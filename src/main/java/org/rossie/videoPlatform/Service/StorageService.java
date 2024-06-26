//package org.rossie.videoPlatform.Service;
//
//import org.rossie.videoPlatform.model.ImageData;
//import org.rossie.videoPlatform.repository.StorageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class StorageService {
//
//    @Autowired
//    private StorageRepository repository;
//
//    @Value("${cloud.aws.s3.bucket-name}")
//    private String bucketName;
//
//
//    public String uploadImage(MultipartFile file){
//        repository.save(ImageData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(file.getBytes())
//                .build()
//        );
//        return null;
//    }
//
//
//    private AmazonS3 s3Client;
//    public String uploadFile(MultipleFile file){
//        s3Client.putObject(bucketName, file.getFileName(), file.getFile());
//        return file.getFileName();
//    }
//}
