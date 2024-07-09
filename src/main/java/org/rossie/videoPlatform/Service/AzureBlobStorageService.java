package org.rossie.videoPlatform.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AzureBlobStorageService {

    @Autowired
    private BlobContainerClient blobContainerClient;

    public String uploadFile(MultipartFile file, String title, String description) throws IOException {

        String fileName = title + "_" + description + "_" + file.getOriginalFilename();
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        BlobSasPermission permission = new BlobSasPermission().setReadPermission(true);
        OffsetDateTime expiryTime = OffsetDateTime.now().plus(1, ChronoUnit.DAYS); // Set the expiry time
        BlobServiceSasSignatureValues values = new BlobServiceSasSignatureValues(expiryTime, permission);

        String sasUrl = blobClient.getBlobUrl() + "?" + blobClient.generateSas(values);
        return sasUrl;
    }
}

