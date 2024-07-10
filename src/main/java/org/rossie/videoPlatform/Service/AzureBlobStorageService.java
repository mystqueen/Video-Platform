package org.rossie.videoPlatform.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class AzureBlobStorageService {

    private final BlobContainerClient containerClient;

    public AzureBlobStorageService(
            @Value("${spring.cloud.azure.storage.blob.account-name}") String accountName,
            @Value("${spring.cloud.azure.storage.account-key}") String accountKey,
            @Value("${spring.cloud.azure.storage.blob.container-name}") String containerName) {

        String connectionString = String.format(
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                accountName, accountKey
        );

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public String uploadFile(MultipartFile file, String fileName) throws IOException {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        try (InputStream inputStream = file.getInputStream()) {
            blobClient.upload(inputStream, file.getSize(), true);
        }

        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());
        blobClient.setHttpHeaders(headers);

        // Generate SAS URL
        BlobSasPermission permission = new BlobSasPermission().setReadPermission(true);
        OffsetDateTime expiryTime = OffsetDateTime.now().plus(1, ChronoUnit.DAYS);
        BlobServiceSasSignatureValues values = new BlobServiceSasSignatureValues(expiryTime, permission);
        System.out.println(blobClient.getBlobUrl() + "?" + blobClient.generateSas(values));

        return blobClient.getBlobUrl() + "?" + blobClient.generateSas(values);
    }
}

